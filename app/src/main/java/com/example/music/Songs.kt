package com.example.music

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Songs : Fragment(), RecycleItemClick {
    companion object {

        lateinit var fullList: ArrayList<MusicModel>
    }

    lateinit var root: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION
        )
        val cursor = requireActivity().managedQuery(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )
        fullList = arrayListOf()
        val songs = ArrayList<MusicModel>()
        while (cursor.moveToNext()) {
            songs.add(
                MusicModel(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),

                )
            )
        }
        fullList = songs
        val musicNames = arrayListOf<String>()
        for (i in
        songs.indices) {
            println(songs[i].audioPath)
            musicNames.add(songs[i].audioTitle)
        }

        MusicControl()
        root = inflater.inflate(R.layout.fragment_music, container, false)
        val recycleItemClick: Songs = this
        val adapter1 = CustomAdapter(fullList, requireActivity(),recycleItemClick)
        val listView = root.findViewById<RecyclerView>(R.id.music_id)
        listView.layoutManager = LinearLayoutManager(requireActivity())
        listView.adapter = adapter1

        listView.setOnClickListener {
        }

        return root
    }

    override fun onItemClick(position: Int) {
        MusicControl.audioPlayer.stop()
        MusicControl.audioPlayer =
            MediaPlayer.create(requireContext(), Uri.parse(fullList[position].audioPath))
        MusicControl.audioPlayer.start()
        val storage = DataStorage(requireActivity())
        storage.savePath(fullList[position].audioPath)
        storage.savePathItem(fullList[position].audioPath)
        storage.saveMArtist(fullList[position].artistName)
        storage.saveMName(fullList[position].audioTitle)
        storage.savePosition(position)
        MainActivity.binding.playMusicId.visibility = View.GONE
        MainActivity.binding.pausedBtnId.visibility = View.VISIBLE
        startActivity(Intent(requireActivity(), MusicActivity::class.java))
        storage.recent(fullList[position].audioPath)

    }
}