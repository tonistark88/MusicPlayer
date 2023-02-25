package com.example.music

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.music.databinding.PlayListLayoutBinding

class PlayListActivity:AppCompatActivity() {
    lateinit var binding: PlayListLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PlayListLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.playListBackBtnId.setOnClickListener {
            finishAndRemoveTask()
        }
    }

    }
