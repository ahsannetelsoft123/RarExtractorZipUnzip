package com.compressfile.zipfile.unzip.fileextractor.utils

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Environment
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

const val PERMISSION_REQUEST_CODE=1122

fun Context.checkPermission(): Boolean {
    return if (SDK_INT >= Build.VERSION_CODES.R) {
        Environment.isExternalStorageManager()
    } else {
        val result =
            ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE)
        val result1 =
            ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE)
        result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
    }
}

fun Activity.requestPermission() {
    if (SDK_INT >= Build.VERSION_CODES.R) {
        try {
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            intent.addCategory("android.intent.category.DEFAULT")
            val uri = Uri.fromParts("package", packageName, null)
            intent.data = Uri.parse(uri.toString())

            startActivityForResult(intent, PERMISSION_REQUEST_CODE)
        } catch (e: Exception) {
            val intent = Intent()
            intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
            startActivityForResult(intent, PERMISSION_REQUEST_CODE)
        }
    } else {
        //below android 11
        ActivityCompat.requestPermissions(
            this,
            arrayOf(WRITE_EXTERNAL_STORAGE),
            PERMISSION_REQUEST_CODE
        )
    }
}