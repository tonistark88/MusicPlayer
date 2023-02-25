package com.example.music

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    private val arrayList: ArrayList<MusicModel>,
    private val context: Context,
    private val recycleItemClick: RecycleItemClick
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val root = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_music_layout, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.musicName.text = arrayList[position].audioTitle
        holder.musicArtist.text = arrayList[position].artistName

        holder.itemView.setOnClickListener {
            recycleItemClick.onItemClick(position)

        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.music_photo_recyclerview)
        val musicName: TextView = itemView.findViewById(R.id.music_name_tv)
        val musicArtist: TextView = itemView.findViewById(R.id.music_artist_id_tv)

    }
}