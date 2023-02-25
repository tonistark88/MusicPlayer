package com.example.music
import android.media.MediaPlayer
import android.net.Uri
import android.net.Uri.parse
import android.view.View
import com.example.music.MainActivity.Companion.context


open class MusicControl {
    companion object {

        var position = 0
        var audioPlayer: MediaPlayer =
            MediaPlayer.create(context, parse(Songs.fullList[position].audioPath))
        val arrayList = ArrayList<UserModel>()
        fun play(): Boolean {
            val storage = DataStorage(context)
            if (audioPlayer.isPlaying) {
                audioPlayer.pause()
            } else if (!audioPlayer.isPlaying) {
                audioPlayer.start()
            }else if (storage.readPath() != " "){
                audioPlayer.stop()
                Songs.fullList[position].audioPath = storage.readPath()
                audioPlayer = MediaPlayer.create(context, parse(Songs.fullList[position].audioPath))
                audioPlayer.start()
            }
            return audioPlayer.isPlaying
        }

        fun next()  {

           // val storage = DataStorage(context)
             //   audioPlayer.stop()
           //         Songs.fullList[position].id
         //   Songs.fullList[position].audioPath = storage.readPathItem()
         //   audioPlayer = MediaPlayer.create(context, parse(Songs.fullList[++position].audioPath))
           // audioPlayer.start()
             //   storage.saveMName(Songs.fullList[position].audioTitle)
               // storage.saveMArtist(Songs.fullList[position].artistName)
          //  storage.savePosition(Songs.fullList[position])
            audioPlayer.stop()
            val storage = DataStorage(context)
            position = storage.readPosition()
            audioPlayer =MediaPlayer.create(context, parse(Songs.fullList[position+1].audioPath))
            audioPlayer.start()
            storage.saveMName(Songs.fullList[position+1].audioTitle)
            storage.saveMArtist(Songs.fullList[position+1].artistName)
            MainActivity.binding.playMusicId.visibility = View.GONE
            MainActivity.binding.pausedBtnId.visibility = View.VISIBLE
            storage.savePosition(position+1)

            }

        fun playback() {
            audioPlayer.stop()
            val storage = DataStorage(context)
            position = storage.readPosition()
            audioPlayer =MediaPlayer.create(context, parse(Songs.fullList[position-1].audioPath))
            audioPlayer.start()
            storage.saveMName(Songs.fullList[position-1].audioTitle)
            storage.saveMArtist(Songs.fullList[position-1].artistName)
            MainActivity.binding.playMusicId.visibility = View.GONE
            MainActivity.binding.pausedBtnId.visibility = View.VISIBLE
            storage.savePosition(position-1)
        }
    }

        fun saveData() {
            val storage = DataStorage(context)
            storage.saveMArtist(arrayList[position].getMusicArtist())
            storage.saveMName(arrayList[position].getMusicName())
        }
        fun playItem() : Boolean {
            audioPlayer.stop()
            audioPlayer = MediaPlayer.create(context, parse(Songs.fullList[position].audioPath))
            audioPlayer.start()
            return audioPlayer.isPlaying
        }


    }