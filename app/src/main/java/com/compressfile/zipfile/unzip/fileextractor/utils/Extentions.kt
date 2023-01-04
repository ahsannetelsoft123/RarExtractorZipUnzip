package com.compressfile.zipfile.unzip.fileextractor.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.content.FileProvider
import com.compressfile.zipfile.unzip.fileextractor.BuildConfig
import com.compressfile.zipfile.unzip.fileextractor.model.FileItem
import java.io.File

fun getMimeTypeFromExtension(url: String?): String? {
    var type: String? = null
    val extension = MimeTypeMap.getFileExtensionFromUrl(url)
    if (extension != null) {
        type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    }
    return type
}

fun Context.openFile(item: FileItem) {
    try {
        val uri= if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(
                this,
                "$packageName.provider",
                File(item.path))
        }else{
            Uri.fromFile( File(item.path))
        }

        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.data = uri
        return startActivity(intent)
    }catch (e:Exception){
        showToast( "No Default application found")
    }

}

fun Context.showToast(string: String){
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Context.fileViewer(item: FileItem){
    try {
        Log.d("fileViewer", "mimeType: $item")
        val mIntent = Intent(Intent.ACTION_VIEW)
        mIntent.setDataAndType(Uri.parse(item.path), item.mimeType)
        startActivity(Intent.createChooser(mIntent, "Open"))
    } catch (e: Exception) {
        Log.d("fileViewer", "Exception: ${e.message}")
    }
}