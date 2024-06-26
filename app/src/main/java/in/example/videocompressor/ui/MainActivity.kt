package `in`.example.videocompressor.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import `in`.example.videocompressor.R
import `in`.example.videocompressor.viewmodel.SelectVideoViewModel
import `in`.example.videocompressor.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: SelectVideoViewModel by viewModels()
    private val videoFetcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it == null) return@registerForActivityResult
        startActivity(Intent(this, VideoSelectedActivity::class.java).apply {
            data = it
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<MainActivityBinding>(
            this,
            R.layout.main_activity
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.showSelectVideo.observe(this) {
            if (it) {
                getVideo()
                viewModel.doneSelection()
            }
        }
    }

    private fun getVideo() {
        videoFetcher.launch("video/*")
    }
}

