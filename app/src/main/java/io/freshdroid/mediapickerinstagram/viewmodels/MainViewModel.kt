package io.freshdroid.mediapickerinstagram.viewmodels

import android.support.design.widget.TabLayout
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.freshdroid.mediapickerinstagram.lib.ActivityViewModel
import io.freshdroid.mediapickerinstagram.lib.Environment


class MainViewModel(
        environment: Environment,
        scopeProvider: AndroidLifecycleScopeProvider
) : ActivityViewModel(), MainViewModelInputs, MainViewModelOutputs {

    val inputs: MainViewModelInputs = this
    val outputs: MainViewModelOutputs = this

    init {

    }

    // INPUTS


}