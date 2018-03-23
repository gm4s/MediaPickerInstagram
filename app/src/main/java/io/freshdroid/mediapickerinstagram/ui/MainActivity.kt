package io.freshdroid.mediapickerinstagram.ui

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import io.freshdroid.mediapickerinstagram.R
import io.freshdroid.mediapickerinstagram.lib.BaseActivity
import io.freshdroid.mediapickerinstagram.lib.extensions.bind
import io.freshdroid.mediapickerinstagram.lib.ui.ViewPagerAdapter
import io.freshdroid.mediapickerinstagram.viewmodels.MainViewModel


class MainActivity : BaseActivity<MainViewModel>() {

    private val mMainViewPager: ViewPager by bind(R.id.mMainViewPager)
    private val mMainTabLayout: TabLayout by bind(R.id.mMainTabLayout)

    override fun onCreate(savedInstanceState: Bundle?) {
        attachViewModel(
                fun(environment, scope): MainViewModel = MainViewModel(environment, scope),
                savedInstanceState
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_view)

        initViews()
    }

    private fun initViews() {
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager, getListFragment())
        mMainViewPager.adapter = pagerAdapter

        mMainTabLayout.addOnTabSelectedListener(getViewPagerOnTabSelectedListener())
        mMainViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mMainTabLayout))

        mMainViewPager.currentItem = 1
    }

    private fun getListFragment(): ArrayList<Fragment> {
        val fragments = ArrayList<Fragment>()

        fragments.add(GalleryPickerFragment.newInstance())
        mMainTabLayout.addTab(mMainTabLayout.newTab().setText(R.string.tab_gallery))

        fragments.add(CapturePhotoFragment.newInstance())
        mMainTabLayout.addTab(mMainTabLayout.newTab().setText(R.string.tab_photo))

        fragments.add(CaptureVideoFragment.newInstance())
        mMainTabLayout.addTab(mMainTabLayout.newTab().setText(R.string.tab_video))

        return fragments
    }

    private fun getViewPagerOnTabSelectedListener(): TabLayout.ViewPagerOnTabSelectedListener {
        return object : TabLayout.ViewPagerOnTabSelectedListener(mMainViewPager) {
            override fun onTabSelected(tab: TabLayout.Tab) {
                super.onTabSelected(tab)
            }
        }
    }

}