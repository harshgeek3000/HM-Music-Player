package com.harshmishra.hmmusicplayer


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.harshmishra.hmmusicplayer.databinding.MusicViewBinding

class MusicAdapter(private val context: Context, private val musicList: ArrayList<music>) : RecyclerView.Adapter<MusicAdapter.MyHolder>() {
    class MyHolder(binding: MusicViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.songnamemv
        val album = binding.songalbummv
        val image  = binding.imgmv
        val duration = binding.songDuration
        val root = binding.root

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(MusicViewBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.title.text = musicList[position].title
        holder.album.text = musicList[position].album
        holder.duration.text = formatDuration(musicList[position].duration)
        Glide.with(context)
            .load(musicList[position].artUri)
            .apply(RequestOptions().placeholder(R.drawable.splah_screen).centerCrop())
            .into(holder.image)
        holder.root.setOnClickListener {
            val intent = Intent(context,Playerhm::class.java)
            intent.putExtra("index",position)
            intent.putExtra("class","MusicAdapter")
            ContextCompat.startActivity(context,intent,null)
        }

    }

    override fun getItemCount(): Int {
        return musicList.size
    }

}