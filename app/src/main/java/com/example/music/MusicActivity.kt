package com.example.music

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.music.databinding.MusicLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlin.concurrent.thread
import kotlin.time.times

class MusicActivity : AppCompatActivity() {
    lateinit var binding: MusicLayoutBinding
    private lateinit var runnable: Runnable
    private val handler = Handler()
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MusicLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tabLayout: TabLayout = findViewById(R.id.player_tab)
        val viewPager: ViewPager = findViewById(R.id.music_photo_layout)
        binding.seekBarId.progress = 0
        binding.seekBarId.max = MusicControl.audioPlayer.duration
        binding.musicFull.text =
            convertMinuteSecondFromMillisecond(MusicControl.audioPlayer.duration)
        val adapter = TabAdapter(supportFragmentManager)
        adapter.addFragmentAndTitle(MusicPhotoActivity(), resources.getString(R.string.Songs))
        adapter.addFragmentAndTitle(Lyrics(), resources.getString(R.string.Lyrics))
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        val storage = DataStorage(this)
       // if (binding.musicStart.equals())
        runnable = Runnable {
            findViewById<TextView>(R.id.music_artist1).text = storage.readMArtist()
            findViewById<TextView>(R.id.music_title1).text = storage.readMName()
            handler.postDelayed(runnable, 100)
        }
        handler.postDelayed(runnable, 10)
        if (MusicControl.audioPlayer.isPlaying) {
            binding.musicPauseAlt.visibility = View.VISIBLE
            binding.musicCledBtn.visibility = View.GONE

        }
        binding.musicCledBtn.setOnClickListener {
            MusicControl.play()
            binding.musicCledBtn.visibility = View.GONE
            binding.musicPauseAlt.visibility = View.VISIBLE
            MainActivity.binding.playMusicId.visibility = View.GONE
            MainActivity.binding.pausedBtnId.visibility = View.VISIBLE
        }
        binding.musicPauseAlt.setOnClickListener {
            MusicControl.play()
            binding.musicPauseAlt.visibility = View.GONE
            binding.musicCledBtn.visibility = View.VISIBLE
            MainActivity.binding.playMusicId.visibility = View.VISIBLE
            MainActivity.binding.pausedBtnId.visibility = View.GONE

        }
        binding.nextBtn.setOnClickListener {
            MusicControl.next()
            storage.saveMName(Songs.fullList[MusicControl.position+1].audioTitle)
            storage.saveMArtist(Songs.fullList[MusicControl.position+1].artistName)
            findViewById<TextView>(R.id.music_artist1).text = storage.readMArtist()
            findViewById<TextView>(R.id.music_title1).text = storage.readMName()
            binding.seekBarId.max = MusicControl.audioPlayer.duration
            binding.musicFull.text =
                convertMinuteSecondFromMillisecond(MusicControl.audioPlayer.duration)


        }
        binding.moreSetting.setOnClickListener {


        }
        binding.musicBackBtn.setOnClickListener {
            MusicControl.playback()
        }
        binding.musicBackBtnId.setOnClickListener {
            finishAndRemoveTask()
        }
        if (storage.isFavorite(Songs.fullList[storage.readPosition()].id)){
            binding.favoriteBtn.visibility = View.GONE
            Log.d("M id: ",Songs.fullList[MusicControl.position].id)
            binding.favoriteBtnEd.visibility = View.VISIBLE}
        binding.favoriteBtn.setOnClickListener {
               binding.favoriteBtn.visibility = View.GONE
            binding.favoriteBtnEd.visibility = View.VISIBLE

            storage.saveFavorite(Songs.fullList[storage.readPosition()].id,true)
        }
        binding.favoriteBtnEd.setOnClickListener {
            binding.favoriteBtn.visibility = View.VISIBLE
            binding.favoriteBtnEd.visibility = View.GONE
            storage.saveFavorite(Songs.fullList[storage.readPosition()].id,false)
//            storage.saveMName(Songs.fullList[MusicControl.position].audioTitle)
//            storage.saveMArtist(Songs.fullList[MusicControl.position].artistName)
        }
        binding.seekBarId.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar?, position: Int, bool: Boolean) {
                if (bool) {
                    binding.musicStart.text =
                        convertMinuteSecondFromMillisecond(MusicControl.audioPlayer.currentPosition)
                    MusicControl.audioPlayer.seekTo(position)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }



        })


        runnable = Runnable {
            binding.musicStart.text =
                convertMinuteSecondFromMillisecond(MusicControl.audioPlayer.currentPosition)
            binding.seekBarId.progress = MusicControl.audioPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        MusicControl.audioPlayer.setOnCompletionListener {
            binding.seekBarId.progress = 0

        }

    }


}

fun convertMinuteSecondFromMillisecond(mSecond: Int): String {
    val minute = mSecond / (1000 * 60)
    val second = mSecond % (1000 * 60) / 1000
    val finalMin = if (minute < 10) {
        "0$minute"
    } else {
        "$minute"
    }

    val finalSec = if (second < 10) {
        "0$second"
    } else {
        "$second"
    }

    return "$finalMin:$finalSec"
}
