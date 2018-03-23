package io.freshdroid.mediapickerinstagram.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.freshdroid.mediapickerinstagram.R
import io.freshdroid.mediapickerinstagram.lib.BaseFragment
import io.freshdroid.mediapickerinstagram.viewmodels.GalleryPickerFragmentViewModel


open class GalleryPickerFragment : BaseFragment<GalleryPickerFragmentViewModel>() {

    companion object {
        fun newInstance(): GalleryPickerFragment {
            return GalleryPickerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        attachViewModel(
                fun(environment, scope): GalleryPickerFragmentViewModel = GalleryPickerFragmentViewModel(environment, scope),
                savedInstanceState
        )
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_gallery_picker, container, false)
    }

}