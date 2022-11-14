package com.example.prj1114

internal class User(var uid: Int, var gender: Int, var email: String, var nickname: String)

internal class Team(
    var tid: Int,
    var time: String,
    var uMax: Int,
    var sta: Int,
    var start: String,
    var end: String,
    uid: Int
) {
    var uCur = 1
    var uid = IntArray(uMax,{0})

    init {
        this.uid[0] = uid
    }

    fun join(uid: Int): Int {
        if (uCur < uMax) {
            this.uid[uCur] = uid
            return 0
        }
        else return 1
    }
}


internal class Chat(var tid: Int, var time: Int, var uid: Int, txt: String?) {
    var txt: String? = null

    init {
        this.tid = tid
        this.txt = txt
    }
}