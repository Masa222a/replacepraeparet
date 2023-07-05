package com.hannibal.replacepraeparet.model

import android.widget.ImageView

class DetailManager {
    private val context = MyApplication.applicationContext()!!
    var item: String = ""

    fun setPhoto(imageView: ImageView, engName: String?) {
        item = engName + "img"
        val pictureId = context.resources.getIdentifier(item,"drawable", context.packageName)
        imageView.setImageResource(pictureId)
    }
}
