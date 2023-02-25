package com.example.music

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.security.AccessControlContext


 class DataBase(val context: Context) :SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){


     lateinit var storage: DataStorage
     override fun onCreate(db: SQLiteDatabase?) {
         TODO("Not yet implemented")
         @Suppress("UNREACHABLE_CODE")
         storage = DataStorage(context)
         val query= "CREATE TABLE $MUSIC_TABLE_NAME("+
                 "$MUSIC_POSITION Integer," +
                 "$MUSIC_NAME text," +
                 "$MUSIC_ARTIST text) "
         db?.execSQL(query)
     }


     override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
         TODO("Not yet implemented"
         )
     }

     companion object {
         const val DATABASE_NAME = "MyDatabase"
         const val DATABASE_VERSION = 1
         const val MUSIC_POSITION = "MusicPosition"
         const val MUSIC_NAME = "MusicName"
         const val MUSIC_ARTIST = "MusicArtist"
         const val MUSIC_TABLE_NAME  = "Music"
     }
 }
