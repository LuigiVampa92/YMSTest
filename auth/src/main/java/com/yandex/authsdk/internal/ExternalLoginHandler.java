package com.yandex.authsdk.internal;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.yandex.authsdk.YandexAuthException;
import com.yandex.authsdk.YandexAuthOptions;
import com.yandex.authsdk.YandexAuthToken;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static android.text.TextUtils.isEmpty;
import static com.yandex.authsdk.YandexAuthException.SECURITY_ERROR;
import static com.yandex.authsdk.internal.Constants.EXTRA_ERROR;
import static com.yandex.authsdk.internal.Constants.EXTRA_LOGIN_HINT;
import static com.yandex.authsdk.internal.Constants.EXTRA_OPTIONS;
import static com.yandex.authsdk.internal.Constants.EXTRA_TOKEN;
import static com.yandex.authsdk.internal.Constants.EXTRA_UID_VALUE;

class ExternalLoginHandler {

    private static final String LOGIN_URL_FORMAT = "https://oauth.yandex.ru/authorize" +
            "?response_type=token" +
            "&client_id=%s" +
            "&redirect_uri=%s" +
            "&state=%s" +
            "&force_confirm=true" +
            "&origin=yandex_auth_sdk_android";

    private static final String REDIRECT_URI_APPLINKS = "https://yx%s.oauth.yandex.ru/auth/finish?platform=android";

    private static final String REDIRECT_URI_SCHEME = "yx%s:///auth/finish?platform=android";

    private static final boolean SUPPORT_APPLINKS = Build.VERSION.SDK_INT >= 23;

    private static final String REDIRECT_URL = SUPPORT_APPLINKS ? REDIRECT_URI_APPLINKS : REDIRECT_URI_SCHEME;

    @NonNull
    private final PreferencesHelper preferencesHelper;

    @NonNull
    private final StateGenerator stateGenerator;

    ExternalLoginHandler(@NonNull final PreferencesHelper preferencesHelper, @NonNull final StateGenerator stateGenerator) {
        this.preferencesHelper = preferencesHelper;
        this.stateGenerator = stateGenerator;
    }

    @NonNull
    String getUrl(@NonNull final String clientId, @Nullable final Long uid, @Nullable final String loginHint) {
        final String state = stateGenerator.generate();
        saveState(state);
        try {
            final String redirectUri = URLEncoder.encode(String.format(REDIRECT_URL, clientId), "UTF-8");
            String url = String.format(LOGIN_URL_FORMAT, clientId, redirectUri, state);

            if (loginHint != null) {
                url += "&login_hint=" + loginHint;
            }

            // What we can do with uid?
            return url;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    String getUrl(@NonNull final Intent intent) {
        final Long uid;
        if (intent.hasExtra(EXTRA_UID_VALUE)) {
            uid = intent.getLongExtra(EXTRA_LOGIN_HINT, 0);
        } else {
            uid = null;
        }

        final String loginHint = intent.getStringExtra(EXTRA_LOGIN_HINT);

        final YandexAuthOptions options = intent.getParcelableExtra(EXTRA_OPTIONS);
        return getUrl(options.getClientId(), uid, loginHint);
    }

    @NonNull
    Intent parseResult(@NonNull final Uri data) {
        final String state = restoreState();
        final String fragment = data.getFragment();

        final Uri dummyUri = Uri.parse("dummy://dummy?" + fragment);

        final Intent result = new Intent();

        final String serverState = dummyUri.getQueryParameter("state");
        if (isEmpty(serverState) || !serverState.equals(state)) {
            result.putExtra(EXTRA_ERROR, new YandexAuthException(SECURITY_ERROR));
            return result;
        }

        final String error = dummyUri.getQueryParameter("error");
        if (error != null) {
            result.putExtra(EXTRA_ERROR, new YandexAuthException(error));
        } else {
            final String token = dummyUri.getQueryParameter("access_token");
            final long expiresIn = Long.parseLong(dummyUri.getQueryParameter("expires_in"));
            result.putExtra(EXTRA_TOKEN, new YandexAuthToken(token, expiresIn));
        }
        return result;
    }

    boolean isFinalUrl(@NonNull final String url, @NonNull final String clientId) {
        return url.startsWith(String.format(REDIRECT_URL, clientId));
    }

    private void saveState(@NonNull final String state) {
        preferencesHelper.saveStateValue(state);
    }

    @Nullable
    private String restoreState() {
        return preferencesHelper.restoreStateValue();
    }

    interface StateGenerator {

        @NonNull
        String generate();
    }
}
