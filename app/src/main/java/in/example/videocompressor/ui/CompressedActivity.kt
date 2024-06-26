package `in`.example.videocompressor.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import `in`.example.videocompressor.viewmodel.CompressedViewModel
import `in`.example.videocompressor.R
import `in`.example.videocompressor.databinding.ActivityCompressedBinding

class CompressedActivity : AppCompatActivity() {
    val viewModel: CompressedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityCompressedBinding>(
            this,
            R.layout.activity_compressed
        )
        intent.data?.let { viewModel.setVideo(it) }
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.videoUri.observe(this) {
            binding.videoView.setVideoURI(it)
            binding.videoView.start()
        }
        viewModel.isPlaying.observe(this) {
            if (it) binding.videoView.start()
            else binding.videoView.pause()
        }
    }
}