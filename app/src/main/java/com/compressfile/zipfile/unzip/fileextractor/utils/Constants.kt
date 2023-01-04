package com.compressfile.zipfile.unzip.fileextractor.utils

class Constants {

    companion object{
        val DCUMENTS_FORMAT:Array<String> = arrayOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document","application/msword")
        val EXCEL_FORMAT:Array<String> = arrayOf("application/vnd.ms-excel","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet","text/csv")
        val POWERPOINT_FORMAT:Array<String> = arrayOf("application/vnd.ms-powerpoint","application/vnd.openxmlformats-officedocument.presentationml.presentation")
        val RICHTEXT_FORMAT:Array<String> = arrayOf("text/rtf")
        val HTML_CSS_FORMAT:Array<String> = arrayOf("text/html","text/css")
        val PHOTOSHOP_FORMAT:Array<String> = arrayOf("image/x-photoshop")
        val PDFS_FORMAT:Array<String> = arrayOf("application/pdf")
        val TEXT_FORMAT:Array<String> = arrayOf("text/plain")
        val APKS_FORMAT:Array<String> = arrayOf("application/vnd.android.package-archive")
    }
}