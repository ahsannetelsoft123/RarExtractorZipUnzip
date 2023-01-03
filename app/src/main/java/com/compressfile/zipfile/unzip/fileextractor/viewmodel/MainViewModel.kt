package com.compressfile.zipfile.unzip.fileextractor.viewmodel

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.compressfile.zipfile.unzip.fileextractor.repository.FileHelper

class MainViewModel : ViewModel() {

    private val SDPath = Environment.getExternalStorageDirectory().absolutePath
    private val dataPath = "$SDPath/ZipManager/zipunzipFile/data/"
    private val zipPath = "$SDPath/ZipManager/zipunzipFile/zip/"
    private val unzipPath = "$SDPath/ZipManager/zipunzipFile/unzip/"
    var inputPath = Environment.getExternalStorageDirectory().path + "/ZipDemo/"
    var inputFile = "Apply.zip"
    var outputPath = Environment.getExternalStorageDirectory().path + "/UnZipDemo/"

     fun unzipFile(context: Context) {
        Log.d("zipManager", "unzipFile: ${zipPath + inputFile}")
        Log.d("zipManager", "unzipPath: $unzipPath")
//        unzip(zipPath + inputFile, unzipPath)
        unZipView(context)
    }

     fun zipFile(context: Context) {
        val s = arrayListOf<String>()
        s.add("$SDPath/ZipManager/zipunzipFile/data")
//        s.add("$SDPath/ZipManager/zipunzipFile/data/image1.jpg")
//        s.add("$SDPath/ZipManager/zipunzipFile/data/image2.jpg")
//        s.add("$SDPath/ZipManager/zipunzipFile/data/image3.jpg")
//        s.add("$SDPath/ZipManager/zipunzipFile/data/image4.jpg")
//        s.add("$SDPath/ZipManager/zipunzipFile/data/dummy2.txt")

//        zip(s, zipPath + inputFile)
        zipView(context)
    }

    fun zipView(context: Context) {
        val folderPath="$SDPath/ZipManager/zipunzipFile/data"
        if (FileHelper.zip(folderPath, zipPath, "dummy1.zip", true)) {
            Toast.makeText(context, "Zip successfully.", Toast.LENGTH_LONG).show()
        }
    }

    fun unZipView(context: Context) {
        if (FileHelper.unzip(zipPath + "dummy1.zip", unzipPath)) {
            Toast.makeText(context, "Unzip successfully.", Toast.LENGTH_LONG).show()
        }
    }
}