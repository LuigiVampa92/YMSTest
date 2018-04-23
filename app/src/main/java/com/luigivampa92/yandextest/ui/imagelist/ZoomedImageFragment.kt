package com.luigivampa92.yandextest.ui.imagelist

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.arellomobile.mvp.MvpAppCompatFragment
import com.github.chrisbanes.photoview.PhotoView
import com.luigivampa92.yandextest.R
import com.luigivampa92.yandextest.routing.transition.ZoomedImageTransitionData
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class ZoomedImageFragment : MvpAppCompatFragment() {

    companion object {
        const val EXTRA_TRANSITION_DATA = "com.luigivampa92.yandextest.ui.zoomed.td"

        fun newInstance(transitionData: ZoomedImageTransitionData): Fragment {
            val arguments = Bundle()
            arguments.putParcelable(EXTRA_TRANSITION_DATA, transitionData)
            val fragment = ZoomedImageFragment()
            fragment.arguments = arguments
            fragment.setHasOptionsMenu(true)
            fragment.setRetainInstance(true)
            return fragment
        }
    }

    private lateinit var unbinder: Unbinder
    @BindView(R.id.image_zoomed)
    protected lateinit var imageZoomed: PhotoView
    @BindView(R.id.progress_bar_loading)
    protected lateinit var progressBarLoading: ProgressBar
    @BindView(R.id.text_error)
    protected lateinit var textViewError: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_zoomed, container, false).also {
                unbinder = ButterKnife.bind(this, it)
                progressBarLoading.visibility = View.VISIBLE
                val fullSizeImageUrl = arguments?.getParcelable<ZoomedImageTransitionData>(EXTRA_TRANSITION_DATA)?.fullSizeImageUrl
                Picasso.get().load(fullSizeImageUrl).into(imageZoomed, object : Callback {
                    override fun onSuccess() {
                        progressBarLoading.visibility = View.GONE
                    }
                    override fun onError(e: Exception?) {
                        textViewError.visibility = View.VISIBLE
                    }
                })
            }

    override fun onDestroyView() {
        unbinder.unbind()
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        setBlackToolbarOn()
    }

    override fun onPause() {
        setBlackToolbarOff()
        super.onPause()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            activity?.supportFragmentManager?.popBackStack()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBlackToolbarOn() {
        val activity = activity as ImageListActivity
        activity.window.statusBarColor = ContextCompat.getColor(activity, R.color.color_black)
        activity.supportActionBar?.let {
            it.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(activity, R.color.color_black)))
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.title = arguments?.getParcelable<ZoomedImageTransitionData>(EXTRA_TRANSITION_DATA)?.title
        }
    }

    private fun setBlackToolbarOff() {
        val activity = activity as ImageListActivity
        activity.window.statusBarColor = ContextCompat.getColor(activity, R.color.color_primary_dark)
        activity.supportActionBar?.let {
            it.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(context!!, R.color.color_primary)))
            it.setDisplayHomeAsUpEnabled(false)
            it.setDisplayShowHomeEnabled(false)
            it.title = getString(R.string.text_title_image_list)
        }
    }
}