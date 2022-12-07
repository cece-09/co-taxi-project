package com.example.prj1114.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prj1114.common.*
import com.example.prj1114.data.*
import com.example.prj1114.databinding.ChatFragmentBinding
import com.example.prj1114.util.NaverLogin
import com.example.prj1114.viewmodel.ChatViewModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class ChatFragment: Fragment(){
    private var mBinding: ChatFragmentBinding? = null
    private val binding get() = mBinding!!
    private var visited: String? = null
    private lateinit var registration: ListenerRegistration
    private val chatList = arrayListOf<Chat>()

    private lateinit var adapter: ChatAdapter
    private lateinit var viewmodel: ChatViewModel
    private lateinit var chatData: ArrayList<Chat>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this)[ChatViewModel::class.java]
        visited = arguments?.getString("visited")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = ChatFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.chatRecyclerview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        adapter = ChatAdapter(viewmodel.currentUser,chatData)
        binding.chatRecyclerview.adapter = adapter

        viewmodel.input.observe(viewLifecycleOwner) {}

        binding.editText.addTextChangedListener { text ->
            viewmodel.setInput(text.toString())
            binding.btnSend.isEnabled = text.isNullOrEmpty().not()
        }

        binding.btnSend.setOnClickListener{
            viewmodel.sendChat()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val enterTime = Date(System.currentTimeMillis())

        if(visited == "Y") {
            CoroutineScope(Dispatchers.Default).launch {
                chatData = viewmodel.getChat()
                chatData.sortBy { it.time }
            }
        }
        else {
            //chatList.add(Chat(userId, teamId, "$userId entered", Timestamp(enterTime)))
        }

//        registration = db.collection(teamId)
//            .orderBy("time",Query.Direction.DESCENDING)
//            .limit(1)
//            .addSnapshotListener { snapshots, e ->
//                if(e!=null){
//                    Log.w(TAG,"listen failed: $e",e)
//                    return@addSnapshotListener
//                }
//                if(snapshots!!.metadata.isFromCache) return@addSnapshotListener
//                for(doc in snapshots.documentChanges){
//                    Log.d(TAG,doc.document.toString())
//                    val item = Chat(
//                        doc.document["time"] as Timestamp,
//                        doc.document["uid"].toString(),
//                        doc.document["txt"].toString()
//                    )
//
//                    if(doc.type == DocumentChange.Type.ADDED && item.time.toDate() > enterTime){
//                        chatList.add(item)
//                    }
//                    adapter.notifyDataSetChanged()
//                    binding.chatRecyclerview.scrollToPosition(chatList.size-1)
//                }
//            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        registration.remove()
        mBinding = null
    }
}