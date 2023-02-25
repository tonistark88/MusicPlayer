package com.example.music

import android.widget.ImageView

class UserModel(private val name: String, private val image: Int, private val subtitle: String, private val audio:Int , private val moreSetting : ImageView) {

    fun getMusicName(): String {
        return name
    }

    fun getMusicImage(): Int {
        return image
    }

    fun getMusicArtist(): String {
        return subtitle
    }
    fun getMusic(): Int {
        return audio
    }

    fun getMoreSetting(): ImageView {
        return moreSetting
    }
}