package com.example.music

import android.annotation.SuppressLint
import android.content.Context





    class DataStorage(var context: Context) {

        companion object {
            const val SHARED_NAME = "RegSharePref"
            const val putExtraNameKey = "KEY_INTENT_NAME"
            const val putExtraKey = "KEY_INTENT_PHONE"
        }

        @SuppressLint("CommitPrefEdits")
        fun saveSharedPrefPhone(gmail: String, password: String) {
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("mail", gmail)
            editor.putString("pass", password)
            editor.apply()
        }

        @SuppressLint("CommitPrefEdits")
        fun saveSharedPrefPhone(phone: String) {
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("phone", phone)
            editor.apply()
        }

        fun saveMName(localeCode: String) {
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("MName", localeCode)
            editor.apply()
        }

        fun readMName(): String {
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            return sharedPref.getString("MName", "ug")!!
        }
        fun saveMArtist(localeCode: String) {
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("MArtist", localeCode)
            editor.apply()
        }

        fun readMArtist(): String {
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            return sharedPref.getString("MArtist", "ug")!!
        }

        fun readPhone(): String? {
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            return sharedPref.getString("phone", "")
        }

        fun readSharePref(key: String): String {
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            return sharedPref.getString(key, "ug")!!
        }

        fun saveSharedPrefProfile(checked: Boolean) {
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean("check", checked)
            editor.apply()

        }

        fun readSharePrefProfile(key: String): Boolean {
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            return sharedPref.getBoolean(key, false)
        }



        fun getIMGTxt(fullName: String): String {
            val txtArr = fullName.split(" ")
            if (txtArr.size > 1)
                return txtArr[0][0] + "" + txtArr[1][0] else return txtArr[0][0] + ""
        }

        fun getCurrentTime(ms: String): String {
            val mSecond = ms.toLong()
            val second = mSecond / 1_000
            val hour = mSecond / (1_000 * 3_600)
            val minute = mSecond % (1_000 * 3_600) / 1_000
            val finalMin = if (minute < 10) {
                "0$minute"
            } else {
                "$minute"
            }

            val finalSec = if (hour < 10) {
                "0$hour"
            } else {
                "$hour"
            }

            return "$finalMin:$finalSec"
        }
        fun savePath(localeCode: String){
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("lastMusic", localeCode)
            editor.apply()


        }
        fun readPath():String{
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            return sharedPref.getString("lastMusic", "")!!
        }
        fun savePathItem(localeCode: String){
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("lastMusicItem", localeCode)
            editor.apply()
    }
        fun readPathItem():String{
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            return sharedPref.getString("lastMusicItem", "local")!!

        }
        fun savePosition(localeCode: Int) {
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putInt("musicPosition", localeCode)
            editor.apply()
        }
        fun readPosition(): Int{
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            return sharedPref.getInt("musicPosition",0 )

        }
        fun recent(localeCode: String) {
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("recent", localeCode)
            editor.apply()
        }
        fun readRecent(): String{
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            return sharedPref.getString("recent","" )!!

        }

            fun saveFavorite(id: String, status:Boolean){
                val sharedPref = context.getSharedPreferences("Favorite", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean(id, status)
                editor.apply()
            }
        fun readFavorite(): String{
            val sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            return sharedPref.getString("recent","" )!!

        }

        fun isFavorite(id:String): Boolean{
            val sharedPref = context.getSharedPreferences("Favorite", Context.MODE_PRIVATE)
            return sharedPref.getBoolean(id,false )!!
        }


    }
