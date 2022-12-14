package com.murat.dialog

import android.content.Context
import android.content.SharedPreferences

class Pref(private val context: Context){
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

    fun getName():String?{
return pref.getString(NAME_PROFILE,"")
    }

    fun saveName(name:String){
        pref.edit().putString(NAME_PROFILE,name).apply()
    }



    companion object {
      private  const val PREF_NAME = "pref.task"
       private const val IMAGE_PROFILE = "image.profile"
       private const val NAME_PROFILE = "name.profile"

    }



}