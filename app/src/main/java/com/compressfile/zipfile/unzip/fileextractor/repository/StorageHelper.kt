package com.compressfile.zipfile.unzip.fileextractor.repository

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.compressfile.zipfile.unzip.fileextractor.utils.getMimeType

class StorageHelper {

    fun getStorageExtFiles(context: Context,fileTypeList: Array<String>) {
        val cr: ContentResolver = context.contentResolver

        val uri: Uri = MediaStore.Files.getContentUri("external")

        for (item in fileTypeList){
            val selectionMimeType = MediaStore.Files.FileColumns.MIME_TYPE + "=?"
            val selectionMultipleArgs = arrayOf(item.getMimeType())
            val cursor = cr.query(uri, null, selectionMimeType, selectionMultipleArgs, null)

            getFilesFromCursor(cursor)
        }

    }

    fun getStorageFiles(context: Context,uri: Uri) {
        val cr: ContentResolver = context.contentResolver
        val cursor = cr.query(uri, null, null, null, null)

        val uriList= arrayListOf<Uri>()
        uriList.add(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        uriList.add(MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        uriList.add(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)

        getFilesFromCursor(cursor)

    }

    fun getFilesFromCursor(cursor: Cursor?){
        val files= arrayListOf<String>()
        cursor?.let {
            if (cursor.count > 0) {
                val mimeColumn : Int = cursor.getColumnIndex(MediaStore.Files.FileColumns.MIME_TYPE)
                val pathColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)

                cursor.use { cursor ->
                    cursor.moveToFirst()
                    do {
                        val mimeType = cursor.getString(mimeColumn)
                        val path = cursor.getString(pathColumn)
                        Log.d("selectionMultipleArgs", "mimeType: $mimeType path: $path")
                        files.add(path)
                    } while (cursor.moveToNext())
                }
            }
        }
    }

}