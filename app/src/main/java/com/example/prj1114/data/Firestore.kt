package com.example.prj1114.data

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MyFirestore() {
    private val TAG = "APPLE/ Firestore"
    private val DB: FirebaseFirestore = Firebase.firestore

    suspend fun create(dto: Dto) {
        DB.collection(dto.collection!!)
            .document(dto.documentId!!)
            .set(dto.data).await()
    }

    suspend fun add(dto: Dto) {
        DB.collection(dto.collection!!)
            .add(dto.data).await()
    }

    suspend fun get(collection: String, documentId: String): DocumentSnapshot {
        return suspendCoroutine { continuation ->
            DB.collection(collection).document(documentId).get()
                .addOnSuccessListener {
                    continuation.resume(it)
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    suspend fun get(collection: String, condition: Pair<String, Any>): ArrayList<QueryDocumentSnapshot>{
        val rtn = arrayListOf<QueryDocumentSnapshot>()
        var collectionRef = DB.collection(collection)

        collectionRef.get()
            .addOnSuccessListener {  }
            .addOnFailureListener {  }



        if(collection=="Team"){
            return suspendCoroutine { continuation ->
                DB.collection(collection)
                    .whereGreaterThanOrEqualTo(condition.first, condition.second)
                    .get()
                    .addOnSuccessListener { documents ->
                        for(doc in documents){
                            rtn.add(doc)
                        }
                        continuation.resume(rtn)
                    }
                    .addOnFailureListener{
                        Log.d(TAG,it.toString())
                        continuation.resumeWithException(it)
                    }
            }
        }
        else {
            return suspendCoroutine { continuation ->
                DB.collection(collection)
                    .whereEqualTo(condition.first, condition.second)
                    .get()
                    .addOnSuccessListener { documents ->
                        for (doc in documents) {
                            rtn.add(doc)
                        }
                        continuation.resume(rtn)
                    }
                    .addOnFailureListener {
                        continuation.resumeWithException(it)
                    }
            }
        }
    }

    fun listen(collection: String): ListenerRegistration {
        return DB.collection(collection)
            .addSnapshotListener { documents, e ->
                if(e != null) return@addSnapshotListener
                documents?.documentChanges
            }
    }
    fun listen(collection: String, order: String): ListenerRegistration {
        return DB.collection(collection)
            .orderBy(order)
            .addSnapshotListener { documents, e ->
                if(e != null) return@addSnapshotListener
                documents?.documentChanges
            }
    }

    suspend fun update(collection: String, documentId: String, key: String, value: String): Void? {
        return DB.collection(collection)
            .document(documentId)
            .update(key, value)
            .await()
    }

    suspend fun delete(collection: String, documentId: String): Void {
        return DB.collection(collection)
            .document(documentId)
            .delete()
            .await()
    }
}