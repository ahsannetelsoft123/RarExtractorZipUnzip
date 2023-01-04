package com.compressfile.zipfile.unzip.fileextractor.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.lifecycle.ViewModelProvider
import com.compressfile.zipfile.unzip.fileextractor.databinding.ActivityFilePickerBinding
import com.compressfile.zipfile.unzip.fileextractor.utils.Constants.Companion.APKS_FORMAT
import com.compressfile.zipfile.unzip.fileextractor.utils.Constants.Companion.DCUMENTS_FORMAT
import com.compressfile.zipfile.unzip.fileextractor.utils.Constants.Companion.EXCEL_FORMAT
import com.compressfile.zipfile.unzip.fileextractor.utils.Constants.Companion.HTML_CSS_FORMAT
import com.compressfile.zipfile.unzip.fileextractor.utils.Constants.Companion.PDFS_FORMAT
import com.compressfile.zipfile.unzip.fileextractor.utils.Constants.Companion.PHOTOSHOP_FORMAT
import com.compressfile.zipfile.unzip.fileextractor.utils.Constants.Companion.POWERPOINT_FORMAT
import com.compressfile.zipfile.unzip.fileextractor.utils.Constants.Companion.RICHTEXT_FORMAT
import com.compressfile.zipfile.unzip.fileextractor.utils.Constants.Companion.TEXT_FORMAT
import com.compressfile.zipfile.unzip.fileextractor.viewmodel.MainViewModel

class FilePickerActivity : AppCompatActivity() {

    lateinit var binding: ActivityFilePickerBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFilePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initViews()
    }

    private fun initViews() {
        binding.imagesFormat.setOnClickListener {
            startViewerActivity(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        }
        binding.videosFormat.setOnClickListener {
            startViewerActivity(MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        }
        binding.audioFormat.setOnClickListener {
            startViewerActivity(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
        }
        binding.documentsFormat.setOnClickListener {
            startViewerActivity(DCUMENTS_FORMAT)
        }
        binding.excelFormat.setOnClickListener {
            startViewerActivity(EXCEL_FORMAT)
        }
        binding.powerPointFormat.setOnClickListener {
            startViewerActivity(POWERPOINT_FORMAT)
        }
        binding.richTextFormat.setOnClickListener {
            startViewerActivity(RICHTEXT_FORMAT)
        }
        binding.photoshopFormat.setOnClickListener {
            startViewerActivity(PHOTOSHOP_FORMAT)
        }
        binding.htmlCssFormat.setOnClickListener {
            startViewerActivity(HTML_CSS_FORMAT)
        }
        binding.pdfsFormat.setOnClickListener {
            startViewerActivity(PDFS_FORMAT)
        }
        binding.textFormat.setOnClickListener {
            startViewerActivity(TEXT_FORMAT)
        }
        binding.apksFormat.setOnClickListener {
            startViewerActivity(APKS_FORMAT)
        }

    }

    private fun startViewerActivity(array: Array<String>){
        val intent=Intent(this,FilesViewerActivity::class.java)
        intent.putExtra("format",array)
        startActivity(intent)
    }

    private fun startViewerActivity(uri: Uri){
        val intent=Intent(this,FilesViewerActivity::class.java)
        intent.putExtra("uri",uri.toString())
        startActivity(intent)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

}