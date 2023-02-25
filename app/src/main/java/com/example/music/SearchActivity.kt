package com.example.music

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.music.databinding.PlayListLayoutBinding
import com.example.music.databinding.SearchPanelLayoutBinding

class SearchActivity : AppCompatActivity() {
    lateinit var binding: SearchPanelLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchPanelLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backBtnId.setOnClickListener {
            finishAndRemoveTask()
        }
    }

}
