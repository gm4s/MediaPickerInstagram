package io.freshdroid.mediapickerinstagram.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.freshdroid.mediapickerinstagram.R
import io.freshdroid.mediapickerinstagram.lib.BaseFragment
import io.freshdroid.mediapickerinstagram.viewmodels.CaptureVideoFragmentViewModel


class CaptureVideoFragment : BaseFragment<CaptureVideoFragmentViewModel>() {

    companion object {
        fun newInstance(): CaptureVideoFragment {
            return CaptureVideoFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        attachViewModel(
                fun(environment, scope): CaptureVideoFragmentViewModel = CaptureVideoFragmentViewModel(environment, scope),
                savedInstanceState
        )
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_capture_video, container, false)
    }

}