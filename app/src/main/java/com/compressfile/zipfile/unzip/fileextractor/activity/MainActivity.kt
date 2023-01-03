package com.compressfile.zipfile.unzip.fileextractor.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.compressfile.zipfile.unzip.fileextractor.databinding.ActivityMainBinding
import com.compressfile.zipfile.unzip.fileextractor.utils.checkPermission
import com.compressfile.zipfile.unzip.fileextractor.utils.requestPermission
import com.compressfile.zipfile.unzip.fileextractor.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!checkPermission()){
            requestPermission()
        }

        initViewModel()
        initViews()

//        getStorageExtFiles(arrayOf("jpg","mp4"))
//        getStorageFiles(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun initViews() {
        binding.zipFile.setOnClickListener {
            viewModel.zipFile(this)
        }
        binding.unzipFile.setOnClickListener {
            viewModel.unzipFile(this)
        }
    }

}