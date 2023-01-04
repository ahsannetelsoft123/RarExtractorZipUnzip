package com.compressfile.zipfile.unzip.fileextractor.model

data class FileItem(
    val path:String,
    val name:String,
    val dateModified:Long?=null,
    val mimeType:String?,
    var isSelected:Boolean=false
)
