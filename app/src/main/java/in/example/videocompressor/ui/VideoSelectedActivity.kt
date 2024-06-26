package `in`.example.videocompressor.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import `in`.example.videocompressor.R
import `in`.example.videocompressor.viewmodel.VideoSelectedViewModel
import `in`.example.videocompressor.databinding.ActivityVideoSelectedBinding

class VideoSelectedActivity : AppCompatActivity() {
    private val viewModel: VideoSelectedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityVideoSelectedBinding>(
            this,
            R.layout.activity_video_selected
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        intent?.data?.let { viewModel.setVideo(it) }
        viewModel.uri.observe(this) { uri ->
            binding.videoView.setVideoURI(uri)
            binding.videoView.start()
        }
        viewModel.error.observe(this) {
            binding.bitrate.error = it
        }
        viewModel.compressing.observe(this) {
            if (it) binding.progressCircular.show()
            else binding.progressCircular.hide()
        }
        viewModel.done.observe(this) {
            if (it != null) {
                startActivity(Intent(this, CompressedActivity::class.java).apply {
                    data = viewModel.uri.value
                })
                finish()
            }
        }
    }
}