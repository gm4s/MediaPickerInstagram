package io.freshdroid.mediapickerinstagram

import io.freshdroid.mediapickerinstagram.lib.Environment


interface MediaPickerInstagramApplicationGraph {

    fun environment(): Environment
    fun inject(mediaPickerInstagramApplication: MediaPickerInstagramApplication)

}