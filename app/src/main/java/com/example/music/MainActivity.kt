package com.example.music

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View

import android.widget.TextView

import androidx.viewpager.widget.ViewPager
import com.example.music.MusicControl.Companion.position

import com.example.music.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import java.net.URI

@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        @SuppressLint("StaticFieldLeak")
        lateinit var binding: ActivityMainBinding
    }

    private lateinit var runnable: Runnable
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        binding.searchBtnId.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
        binding.favoritesBtnId.setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }
        binding.playlistBtnId.setOnClickListener {
            startActivity(Intent(this, PlayListActivity::class.java))
        }
        binding.recentBtnId.setOnClickListener {
            startActivity(Intent(this, RecentActivity::class.java))
        }
        binding.musicStartBtnId.setOnClickListener {
            startActivity(Intent(this, MusicActivity::class.java))
        }

        val tabLayout: TabLayout = findViewById(R.id.table_layout)
        val viewPager: ViewPager = findViewById(R.id.view_pager_id)
        val adapter = TabAdapter(supportFragmentManager)
        adapter.addFragmentAndTitle(Songs(), resources.getString(R.string.Songs))
        adapter.addFragmentAndTitle(Artists(), resources.getString(R.string.Artists))
        adapter.addFragmentAndTitle(Albums(), resources.getString(R.string.Album))
        adapter.addFragmentAndTitle(Folders(), resources.getString(R.string.folder))
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        val storage = DataStorage(this)

        runnable = Runnable {
            findViewById<TextView>(R.id.music_artist).text = storage.readMArtist()
            findViewById<TextView>(R.id.music_subtitle).text = storage.readMName()

            handler.postDelayed(runnable, 100)
        }
        handler.postDelayed(runnable, 100)
        binding.playMusicId.setOnClickListener {
            MusicControl.play()
            binding.playMusicId.visibility = View.GONE
            binding.pausedBtnId.visibility = View.VISIBLE


        }
        binding.pausedBtnId.setOnClickListener {
            MusicControl.play()
            binding.pausedBtnId.visibility = View.GONE
            binding.playMusicId.visibility = View.VISIBLE


        }
        binding.nextMusic.setOnClickListener {
           MusicControl.next()
        }



    }



}
