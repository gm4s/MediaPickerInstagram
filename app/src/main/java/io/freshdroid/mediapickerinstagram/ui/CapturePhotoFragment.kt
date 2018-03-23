package io.freshdroid.mediapickerinstagram.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.freshdroid.mediapickerinstagram.R
import io.freshdroid.mediapickerinstagram.lib.BaseFragment
import io.freshdroid.mediapickerinstagram.viewmodels.CapturePhotoFragmentViewModel


class CapturePhotoFragment: BaseFragment<CapturePhotoFragmentViewModel>() {

    companion object {
        fun newInstance(): CapturePhotoFragment {
            return CapturePhotoFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        attachViewModel(
                fun(environment, scope): CapturePhotoFragmentViewModel = CapturePhotoFragmentViewModel(environment, scope),
                savedInstanceState
        )
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_capture_photo, container, false)
    }

}