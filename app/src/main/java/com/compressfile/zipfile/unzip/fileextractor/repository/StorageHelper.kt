package com.compressfile.zipfile.unzip.fileextractor.repository

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.compressfile.zipfile.unzip.fileextractor.model.FileItem

object StorageHelper {

    fun getSpecificExtFiles(context: Context, fileTypeList: Array<String>): ArrayList<FileItem> {
        val fileList = arrayListOf<FileItem>()
        val cr: ContentResolver = context.contentResolver
        val uri: Uri = MediaStore.Files.getContentUri("external")

        for (item in fileTypeList) {
            val selectionMimeType = MediaStore.Files.FileColumns.MIME_TYPE + "=?"
            Log.d("getSpecificExtFiles", "getSpecificExtFiles: ${item}")
            val selectionMultipleArgs = arrayOf(item)
            val cursor = cr.query(uri, null, selectionMimeType, selectionMultipleArgs, null)

            fileList.addAll(getFilesFromCursor(cursor))
        }

        if (fileTypeList.size>1){
            fileList.sortBy { it.dateModified }
        }

        return fileList
    }

    fun getFilesWithUri(context: Context, uri: Uri): ArrayList<FileItem> {
        val cr: ContentResolver = context.contentResolver
        val cursor = cr.query(uri, null, null, null, null)

        return getFilesFromCursor(cursor)
    }

    private fun getFilesFromCursor(cursor: Cursor?): ArrayList<FileItem> {
        val files = arrayListOf<FileItem>()
        cursor?.let {
            if (cursor.count > 0) {
                val mimeColumn: Int = cursor.getColumnIndex(MediaStore.Files.FileColumns.MIME_TYPE)
                val pathColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
                val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME)
                val dateModifiedColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_MODIFIED)

                cursor.use { cursor ->
                    cursor.moveToFirst()
                    do {
                        val mimeType = cursor.getString(mimeColumn)
                        val path = cursor.getString(pathColumn)
                        val name = cursor.getString(nameColumn)
                        val dateModified = cursor.getLong(dateModifiedColumn)
                        Log.d("selectionMultipleArgs", "mimeType: $mimeType path: $path")
                        files.add(FileItem(path, name,dateModified,mimeType))

                    } while (cursor.moveToNext())
                }
            }
        }
        return files
    }

}