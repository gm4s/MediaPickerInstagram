package io.freshdroid.mediapickerinstagram.lib.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class ViewPagerAdapter(
        fragmentManager: FragmentManager,
        private val fragments: ArrayList<Fragment>
) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment = fragments[position]


    override fun getCount(): Int = fragments.size

}