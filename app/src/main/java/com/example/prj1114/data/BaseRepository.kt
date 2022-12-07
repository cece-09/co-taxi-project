package com.example.prj1114.data

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.toObject

class BaseRepository() {
    private val tag = "APPLE/ Repository"
    private val firestore = MyFirestore()

    suspend fun create(dto: Dto) {
        return when(dto.documentId.isNullOrEmpty()) {
            true -> firestore.add(dto)
            false -> firestore.create(dto)
        }
    }
    suspend fun get(collection: String, documentId: String): DocumentSnapshot {
        return firestore.get(collection, documentId)
    }
    suspend fun get(collection: String, condition: Pair<String, Any>): ArrayList<QueryDocumentSnapshot> {
        return firestore.get(collection, condition)
    }
    suspend fun delete(collection: String, documentId: String): Void {
        return firestore.delete(collection, documentId)
    }
    fun listen(collection: String): ListenerRegistration {
        return firestore.listen(collection)
    }
    fun listen(collection: String, order: String): ListenerRegistration {
        return firestore.listen(collection, order)
    }
}

class ChatRepository() {
    private val baseRepository = BaseRepository()

    suspend fun create(chat: ChatDto) {
        return baseRepository.create(chat)
    }

    suspend fun get(condition: Pair<String, Any>)
    : ArrayList<Chat>{
        val rtn = arrayListOf<Chat>()
        val snapshots = baseRepository.get("Chat", condition)
        for (doc in snapshots) {
            rtn.add(doc.toObject<ChatDto>().asDomain())
        }
        return rtn
    }

    suspend fun listen(collection: String) {
        TODO()
    }
}

class TeamRepository() {
    private val baseRepository = BaseRepository()

    suspend fun create(team: TeamDto) {
        return baseRepository.create(team)
    }
    suspend fun get(documentId: String): Team2? {
        val snapshot = baseRepository.get("Team", documentId)
        Log.d("APPLE", "$snapshot")
        return snapshot.toObject<Team2>()
    }
    suspend fun get(condition: Pair<String, Any>): ArrayList<Team>{
        val rtn = arrayListOf<Team>()
        val snapshots = baseRepository.get("Team", condition)
        for (doc in snapshots) {
            rtn.add(doc.toObject<TeamDto>().asDomain())
        }
        return rtn
    }
}