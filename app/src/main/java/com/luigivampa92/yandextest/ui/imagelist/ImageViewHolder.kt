package com.luigivampa92.yandextest.ui.imagelist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.luigivampa92.yandextest.R
import com.luigivampa92.yandextest.YandexTestApplication
import com.luigivampa92.yandextest.domain.model.Image
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ImageViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        private val onClickListener: (Image) -> Unit
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_image, parent, false)) {

    @BindView(R.id.image_preview)
    protected lateinit var imagePreview: ImageView

    @Inject
    lateinit var picasso: Picasso

    private lateinit var image: Image

    init {
        YandexTestApplication.INSTANCE.getAppComponent().inject(this)
        ButterKnife.bind(this, itemView)
    }

    fun bind(image: Image) {
        this.image = image

        picasso.load(image.preview)
                .placeholder(R.drawable.icon_picture_stub)
                .error(R.drawable.icon_picture_stub_error)
                .into(imagePreview)
    }

    @OnClick(R.id.image_preview)
    protected fun itemClicked() {
        onClickListener.invoke(image)
    }
}