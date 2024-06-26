package `in`.example.videocompressor.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CompressedViewModel : ViewModel() {
    private val _isPlaying = MutableLiveData(false)
    val isPlaying: LiveData<Boolean> = _isPlaying
    private val _videoUri = MutableLiveData<Uri>()
    val videoUri: LiveData<Uri> = _videoUri
    fun setVideo(uri: Uri) {
        _videoUri.value = uri
        _isPlaying.value = true
    }

    fun togglePause() {
        _isPlaying.value = _isPlaying.value?.not()
    }
}