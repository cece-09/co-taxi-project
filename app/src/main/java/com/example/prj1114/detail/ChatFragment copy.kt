//package com.example.prj1114.detail
//
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.core.widget.addTextChangedListener
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.prj1114.common.*
//import com.example.prj1114.model.*
//import com.example.prj1114.databinding.ChatFragmentBinding
//import com.google.firebase.Timestamp
//import com.google.firebase.firestore.DocumentChange
//import com.google.firebase.firestore.ListenerRegistration
//import com.google.firebase.firestore.Query
//import java.util.*
//
//class ChatFragment: Fragment(){
//    private var mBinding: ChatFragmentBinding? = null
//    private val binding get() = mBinding!!
//    private lateinit var currentUser:String
//    private var visited: String? = null
//    private lateinit var registration: ListenerRegistration
//    private val chatList = arrayListOf<Chat>()
//    private lateinit var adapter: ChatAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        currentUser = userId
//        visited = arguments?.getString("visited")
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        mBinding = ChatFragmentBinding.inflate(inflater, container, false)
//        val view = binding.root
//        Log.d(TAG,"currentUser:${currentUser}")
//
//        binding.chatRecyclerview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
//        adapter = ChatAdapter(currentUser,chatList)
//        binding.chatRecyclerview.adapter = adapter
//
//        binding.editText.addTextChangedListener { text ->
//            binding.btnSend.isEnabled = text.toString() != ""
//        }
//
//        binding.btnSend.setOnClickListener{
//            val data = Chat(Timestamp.now(), userId, binding.editText.text.toString())
//
//            db.collection(teamId).add(data)
//                .addOnSuccessListener {
//                    binding.editText.text.clear()
//                    Log.d(TAG, "document added: $it")
//                }
//                .addOnFailureListener { e ->
//                    Toast.makeText(context, "send failed",Toast.LENGTH_SHORT).show()
//                    Log.w(TAG,"error occurs: $e",e)
//                }
//        }
//        return view
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val enterTime = Date(System.currentTimeMillis())
//
//        if(visited == "Y") {
//            db.collection(teamId).orderBy("time", Query.Direction.DESCENDING)
//                .limit(10)
//                .get()
//                .addOnSuccessListener { documents ->
//                    for (document in documents) {
//                        Log.d(TAG, document.toString())
//                        val item = Chat(
//                            document["time"] as Timestamp,
//                            document["uid"].toString(),
//                            document["txt"].toString()
//                        )
//                        chatList.add(item)
//                    }
//                    chatList.sortBy { it.time }
//                    binding.chatRecyclerview.scrollToPosition(chatList.size-1)
//                    adapter.notifyDataSetChanged()
//                }
//                .addOnFailureListener { exception ->
//                    Log.w(TAG, "error getting documents: $exception", exception)
//                }
//        }
//        else {
//            chatList.add(Chat(Timestamp(enterTime), "", "$currentUser entered"))
//        }
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
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        registration.remove()
//        mBinding = null
//    }
//}