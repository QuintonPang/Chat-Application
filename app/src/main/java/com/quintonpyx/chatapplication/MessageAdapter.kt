package com.quintonpyx.chatapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import java.util.*

// RecyclerView.ViewHolder is a generic
class MessageAdapter(val context: Context, val messageList: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVED = 1
    val ITEM_SENT = 2

    class SentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val sentMessage = itemView.findViewById<TextView>(R.id.txt_sent_message)
    }

    class ReceivedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val receivedMessage  = itemView.findViewById<TextView>(R.id.txt_received_message)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

//        Log.d("TAG","RAN")

        if(viewType==1){
            // inflate received
            val view:View = LayoutInflater.from(context).inflate(R.layout.received_msg ,parent,false)
        Log.d("TAG","RAN1")
            return ReceivedViewHolder(view)

        }else{
            // inflate sent
            val view:View = LayoutInflater.from(context).inflate(R.layout.sent_msg ,parent,false)
//            Log.d("TAG","RAN2")
            return SentViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        return if(currentMessage.senderId?.equals(FirebaseAuth.getInstance().currentUser?.uid) == true){
            ITEM_SENT
        }else{
            ITEM_RECEIVED
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]

        if(holder.javaClass==SentViewHolder::class.java){
            // sent view holder logic
            val viewHolder = holder as SentViewHolder
            viewHolder.sentMessage.text = currentMessage.message
        }else{
            // receive view holder logic
            val viewHolder = holder as ReceivedViewHolder
            viewHolder.receivedMessage.text = currentMessage.message

        }
    }

    override fun getItemCount(): Int {
        Log.d("TAG",messageList.size.toString())

        return messageList.size
    }
}