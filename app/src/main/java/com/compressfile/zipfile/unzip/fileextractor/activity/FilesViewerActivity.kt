package com.compressfile.zipfile.unzip.fileextractor.activity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.compressfile.zipfile.unzip.fileextractor.R
import com.compressfile.zipfile.unzip.fileextractor.adapter.FilesViewerAdapter
import com.compressfile.zipfile.unzip.fileextractor.databinding.ActivityFilesViewerBinding
import com.compressfile.zipfile.unzip.fileextractor.viewmodel.MainViewModel

class FilesViewerActivity : AppCompatActivity() {

    lateinit var binding: ActivityFilesViewerBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFilesViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initViews()

    }

    private fun initViews() {
        val adapter=FilesViewerAdapter()
        adapter.fileEvents=object :FilesViewerAdapter.FileEvents{
            override fun onSelectItem(count: Int) {
                binding.selectedCount.text="$count Item Selected"
            }
        }
        binding.multipleSelectionSwitch.setOnCheckedChangeListener { compoundButton, b ->
            adapter.multipleSelection(b)
            if (!b){
                binding.selectedCount.text="0 Item Selected"
            }
        }


        binding.recyclerView.apply {
            layoutManager=GridLayoutManager(this@FilesViewerActivity,2)
            this.adapter=adapter
        }

        getFiles()
        viewModel.fileList.observe(this){
            adapter.setList(it)

            if (it.isNullOrEmpty()){
                binding.tvNoItemFound.visibility=View.VISIBLE
            }else{
                binding.tvNoItemFound.visibility=View.GONE
            }
        }
    }

    private fun getFiles(){
        intent.getStringExtra("uri")?.let {
            viewModel.getStorageFiles(this, Uri.parse(it))
        }
        intent.getStringArrayExtra("format")?.let {
            viewModel.getStorageExtFiles(this,it)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

}