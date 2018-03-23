package io.freshdroid.mediapickerinstagram.lib

import android.content.Context
import android.os.Bundle
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.freshdroid.mediapickerinstagram.MediaPickerInstagramApplication
import io.freshdroid.mediapickerinstagram.lib.utils.BundleUtils
import java.util.*

private const val VIEW_MODEL_ID_KEY = "view_model_id"
private const val VIEW_MODEL_STATE_KEY = "view_model_state"

object ActivityViewModelManager {

    private val viewModels = HashMap<String, ActivityViewModel>()

    fun <ViewModelType : ActivityViewModel> fetch(context: Context,
                                                  scopeProvider: AndroidLifecycleScopeProvider,
                                                  activityViewModelConstructor: ActivityViewModelConstructor,
                                                  savedInstanceState: Bundle?): ViewModelType {

        val viewModelId = fetchId(savedInstanceState)
        var activityViewModel: ActivityViewModel? = viewModels[viewModelId]

        if (activityViewModel == null) {
            activityViewModel = create(context, scopeProvider, activityViewModelConstructor, savedInstanceState, viewModelId)
        }

        return activityViewModel as ViewModelType
    }

    fun save(activityViewModel: ActivityViewModel, envelope: Bundle) : Bundle {
        envelope.putString(VIEW_MODEL_ID_KEY, findIdForViewModel(activityViewModel))

        val state = Bundle()
        envelope.putBundle(VIEW_MODEL_STATE_KEY, state)

        return envelope
    }

    fun destroy(activityViewModel: ActivityViewModel) {
        activityViewModel.onDestroy()

        val iterator = viewModels.entries.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (activityViewModel == entry.value) {
                iterator.remove()
            }
        }
    }

    private fun <ViewModelType : ActivityViewModel> create(context: Context,
                                                           scopeProvider: AndroidLifecycleScopeProvider,
                                                           activityViewModelConstructor: ActivityViewModelConstructor,
                                                           savedInstanceState: Bundle?,
                                                           viewModelId: String): ViewModelType {

        val application = context.applicationContext as MediaPickerInstagramApplication
        val environment = application.component().environment()

        val viewModel = activityViewModelConstructor(environment, scopeProvider)

        viewModels[viewModelId] = viewModel

        viewModel.onCreate(context, BundleUtils.maybeGetBundle(savedInstanceState, VIEW_MODEL_STATE_KEY))

        return viewModel as ViewModelType
    }

    private fun fetchId(savedInstanceState: Bundle?): String {
        return if (savedInstanceState != null) savedInstanceState.getString(VIEW_MODEL_ID_KEY) else UUID.randomUUID().toString()
    }

    private fun findIdForViewModel(activityViewModel: ActivityViewModel): String {
        for ((key, value) in viewModels) {
            if (activityViewModel == value) {
                return key
            }
        }
        throw RuntimeException("Cannot find view model in map!")
    }

}