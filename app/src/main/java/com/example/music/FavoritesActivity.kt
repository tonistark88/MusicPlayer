package com.example.music

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.music.databinding.FavoritesBinding
import com.example.music.databinding.PlayListLayoutBinding

class FavoritesActivity : AppCompatActivity(), RecycleItemClick {
    lateinit var binding: FavoritesBinding
    lateinit var postion:ArrayList<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
         lateinit var runnable: Runnable
        val handler = Handler()
        super.onCreate(savedInstanceState)
        binding = FavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val storage = DataStorage(this)
        runnable = Runnable {
            findViewById<TextView>(R.id.music_artist).text = storage.readMArtist()
            findViewById<TextView>(R.id.music_subtitle).text = storage.readMName()

            handler.postDelayed(runnable, 100)
        }
        handler.postDelayed(runnable, 100)
        binding.playMusicId.setOnClickListener {
            MusicControl.play()
            binding.playMusicId.visibility =View.GONE
            binding.pausedBtnId.visibility = View.VISIBLE
            MainActivity.binding.playMusicId.visibility = View.GONE
         MainActivity.binding.pausedBtnId.visibility = View.VISIBLE
        }
        binding.pausedBtnId.setOnClickListener {
            MusicControl.play()
            binding.playMusicId.visibility =View.VISIBLE
            binding.pausedBtnId.visibility = View.GONE
          MainActivity.binding.playMusicId.visibility =View.VISIBLE
            MainActivity.binding.pausedBtnId.visibility = View.GONE
        }
        binding.nextMusic.setOnClickListener {
            MusicControl.next()
        }
        binding.musicStartBtnId.setOnClickListener {
            startActivity(Intent(this, MusicActivity::class.java))
        }

val al = Songs.fullList

      postion = ArrayList()

        Log.d("size",al.size.toString())
        val favList = ArrayList<MusicModel>()
        for ( i in al.indices){
            Log.d("status",storage.isFavorite(al[i].id).toString())
            if (storage.isFavorite(al[i].id)){
                favList.add(al[i])

                postion.add(i)
            }
        }
        Log.d("status of favlist",favList.size.toString())

        val recycleItemClick: FavoritesActivity = this

        val rv = findViewById<RecyclerView>(R.id.fav_rv)
        rv.layoutManager = LinearLayoutManager(this)

        rv.adapter = CustomAdapter(favList,this,recycleItemClick)
        binding.favoritesBackBtnId.setOnClickListener {
            finishAndRemoveTask()
        }

    }

    override fun onItemClick(p: Int) {
        MusicControl.audioPlayer.stop()
        val position = postion[p]
        MusicControl.audioPlayer =
            MediaPlayer.create(this, Uri.parse(Songs.fullList[position].audioPath))
        MusicControl.audioPlayer.start()
        val storage = DataStorage(this)
        storage.savePath(Songs.fullList[position].audioPath)
        storage.savePathItem(Songs.fullList[position].audioPath)
        storage.saveMArtist(Songs.fullList[position].artistName)
        storage.saveMName(Songs.fullList[position].audioTitle)
        storage.savePosition(position)
        MainActivity.binding.playMusicId.visibility = View.GONE
        MainActivity.binding.pausedBtnId.visibility = View.VISIBLE
        binding.playMusicId.visibility =View.GONE
        binding.pausedBtnId.visibility = View.VISIBLE
        startActivity(Intent(this, MusicActivity::class.java))
        storage.recent(Songs.fullList[position].audioPath)

    }


}