package com.example.prj1114.detail

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.prj1114.data.*
import com.example.prj1114.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ChatAdapter(
    private val currentUser: String,
    private val itemList: ArrayList<Chat>
) :RecyclerView.Adapter<ChatAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):ChatAdapter.ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return itemList.size
    }
    override fun onBindViewHolder(holder:ChatAdapter.ViewHolder, position:Int){
        if(currentUser == itemList[position].userId){
            holder.card.setCardBackgroundColor(Color.parseColor("#FFF176"))
        }
        holder.nickname.text = itemList[position].userId
        holder.contents.text = itemList[position].text
        val sf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.KOREA)
        sf.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        holder.time.text = sf.format(itemList[position].time.toDate())
    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val card:CardView = itemView.findViewById(R.id.chat_card_view)
        val nickname:TextView = itemView.findViewById(R.id.nickname)
        val contents:TextView = itemView.findViewById(R.id.contents)
        val time:TextView = itemView.findViewById(R.id.time)
    }
}