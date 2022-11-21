package com.murat.dialog

import android.content.Context
import android.content.SharedPreferences

class Pref(private val context: Context) {
    private var pref: SharedPreferences = context.getSharedPreferences(PREF_NAME,
        Context.MODE_PRIVATE
    )


    fun saveImage(image: String) {

        pref.edit().putString(IMAGE_PROFILE, image).apply()
    }

    fun deleteImage(){
        pref.edit().remove(IMAGE_PROFILE).apply()
    }


    fun getImage(): String? {
        return pref.getString(IMAGE_PROFILE, "")
    }




    companion object {
        private const val PREF_NAME = "pref.task"
        private const val IMAGE_PROFILE = "image.profile"
    }


}