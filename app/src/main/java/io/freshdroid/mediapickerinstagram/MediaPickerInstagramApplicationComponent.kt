package io.freshdroid.mediapickerinstagram

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MediaPickerInstagramApplicationModule::class])
interface MediaPickerInstagramApplicationComponent : MediaPickerInstagramApplicationGraph {
}