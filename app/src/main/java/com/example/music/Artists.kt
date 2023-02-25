package com.example.music

import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Artists: Fragment(), RecycleItemClick {
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
        val   cursor = requireActivity().managedQuery(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )
        var fullList= arrayListOf<MusicModel>()
        val songs= ArrayList<MusicModel>()
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
        fullList=songs
        val musicNames = arrayListOf<String>()
        for (i in
        songs.indices){
            println(songs[i].audioPath)
            musicNames.add(songs[i].audioTitle)
        }
        MusicControl()
        val root = inflater.inflate(R.layout.fragment_music, container, false)
        val recycleItemClick: RecycleItemClick = this
        val adapter1 = CustomAdapter(fullList, requireActivity(), recycleItemClick)
        val listView = root.findViewById<RecyclerView>(R.id.music_id)
        listView.layoutManager = LinearLayoutManager(requireActivity())
        listView.adapter = adapter1

        listView.setOnClickListener {
            Toast.makeText(requireActivity(), "hello", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onItemClick(position: Int) {
    }
}