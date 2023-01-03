package com.compressfile.zipfile.unzip.fileextractor.utils

import android.webkit.MimeTypeMap

fun String.getMimeType():String?{
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(this)
}