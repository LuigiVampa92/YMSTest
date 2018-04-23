package com.luigivampa92.yandextest.ui.imagelist

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.luigivampa92.yandextest.domain.presenter.ImageListPresenter
import com.luigivampa92.yandextest.R
import com.luigivampa92.yandextest.domain.model.Image
import com.luigivampa92.yandextest.ui.base.BaseFragment
import com.luigivampa92.yandextest.util.alertConformation
import javax.inject.Inject

class ImageListFragment : BaseFragment(), ImageListView {

    companion object {
        private const val FETCH_ITEMS_COUNT = 10

        fun newInstance(): ImageListFragment {
            val fragment = ImageListFragment()
            fragment.setHasOptionsMenu(true)
            return fragment
        }
    }

    private lateinit var unbinder: Unbinder
    @BindView(R.id.recycler_view_images)
    protected lateinit var recyclerViewImages: RecyclerView
    @BindView(R.id.text_no_items)
    protected lateinit var textEmpty: TextView
    @BindView(R.id.progress_bar_loading)
    protected lateinit var progressBarLoading: ProgressBar
    @BindView(R.id.progress_bar_fetching)
    protected lateinit var progressBarFetching: ProgressBar

    @Inject
    @InjectPresenter
    lateinit var presenter: ImageListPresenter
    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var adapter: ImageListRecyclerViewAdapter
    private lateinit var layoutManager: GridLayoutManager

    private var notResponded: Boolean = true
    private var fetching: Boolean = false
    private var countPast = 0
    private var countVisible = 0
    private var countTotal = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_imagelist, container, false).also {
                unbinder = ButterKnife.bind(this, it)

                adapter = ImageListRecyclerViewAdapter(context!!, presenter::imageClicked)
                layoutManager = GridLayoutManager(context!!, resources.getInteger(R.integer.grid_span))

                recyclerViewImages.adapter = adapter
                recyclerViewImages.layoutManager = layoutManager
            }

    override fun onDestroyView() {
        unbinder.unbind()
        super.onDestroyView()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.add(1, 1, 1, getString(R.string.text_menu_iem_deauth))
                ?.setIcon(R.drawable.icon_exit)
                ?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == 1) {
            alertConformation(this, R.string.text_confirm_deauth, presenter::deauth)
            return true
        }
        else {
            return super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        recyclerViewImages.addOnScrollListener(scrollListener)
        presenter.loadImages(FETCH_ITEMS_COUNT, adapter.itemCount)
    }

    override fun onPause() {
        recyclerViewImages.clearOnScrollListeners()
        super.onPause()
    }

    override fun showLoading(visible: Boolean) {
        progressBarLoading.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun showImages(images: List<Image>) {
        if (images.isNotEmpty()) {
            notResponded = false
        }

        adapter.addItems(images)
        showEmpty(adapter.itemCount == 0)

        if (fetching && progressBarFetching.visibility == View.VISIBLE) {
            fetching = false
            showProgressBarFetching(false)
        }
    }

    override fun showEmpty(visible: Boolean) {
        textEmpty.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun showProgressBarFetching(visible: Boolean) {
        progressBarFetching.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private var scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            if (dy > 0) {
                countVisible = layoutManager.childCount
                countTotal = layoutManager.itemCount
                countPast = layoutManager.findFirstVisibleItemPosition()

                if (!fetching) {
                    if ((countVisible + countPast) >= countTotal) {
                        fetching = true
                        showProgressBarFetching(true)
                        presenter.loadImages(FETCH_ITEMS_COUNT, adapter.itemCount)
                    }
                }
            }
        }
    }
}