package com.example.prj1114.data

import com.google.firebase.Timestamp

interface Domain {
    fun asDto(): Dto
}

interface Dto {
    val collection: String?
    val documentId: String?
    val data: Map<String, Any?>
    fun asDomain(): Domain
}

data class User(
    val userId: String,
    var nickname: String,
    val gender: String
): Domain {
    override fun asDto(): UserDto {
        return UserDto(
            collection = "User",
            documentId = userId,
            data = mapOf(
                "nickname" to nickname,
                "gender" to gender
            )
        )
    }
}

data class UserDto(
    override val collection: String? = null,
    override val documentId: String? = null,
    override val data: Map<String, Any?> = mapOf(
        "nickname" to null,
        "gender" to null,
    )
): Dto {
    override fun asDomain(): User {
        return User(
            userId = this.documentId.orEmpty(),
            nickname = this.data["nickname"].toString().orEmpty(),
            gender = this.data["gender"].toString().orEmpty()
        )
    }
}

data class TeamDto(
    override val collection: String? = null,
    override val documentId: String? = null,
    override val data: Map<String, Any?> = mapOf(
        "time" to Long,
        "start" to HashMap<String, String>(),
        "end" to HashMap<String, String>(),
        "status" to Int,
        "max" to Int,
        "curr" to Int
    )
): Dto {
    override fun asDomain(): Team {
        return Team(
            teamId = this.documentId.orEmpty(),
            time   = this.data["time"] as Long,
            start  = this.data["start"] as Juso,
            end    = this.data["end"]  as Juso,
            status = (this.data["data"] ?: -1) as Int,
            max    = (this.data["max"] ?: -1) as Int,
            curr   = (this.data["curr"] ?: -1) as Int
        )
    }
    fun HashMap<String, String>.asJuso(): Juso {
        return Juso(
            this["roadAddr"]!!,
            this["endAddr"]!!,
            this["zipNo"]!!,
            this["bdNm"]!!,
            this["siNm"]!!,
            this["sggNm"]!!,
            this["emdNm"]!!
        )
    }
}

data class Team2(
    val time: Long = 0,
    val start: HashMap<String, String> = hashMapOf<String, String>(),
    val end: HashMap<String, String> = hashMapOf<String, String>(),
    val status: Int = 0,
    val max: Int = 4,
    val curr: Int = 1
)

data class Team(
    val teamId: String,
    val time: Long,
    val start: Juso,
    val end: Juso,
    val status: Int,
    val max: Int,
    val curr: Int
): Domain {
    override fun asDto(): TeamDto {
        return TeamDto(
            collection = "Team",
            documentId = teamId,
            data = mapOf(
                "time" to time,
                "start" to start,
                "end" to end,
                "status" to status,
                "max" to max,
                "curr" to curr
            )
        )
    }
}

data class Chat(
    val userId: String,
    val teamId: String,
    val text: String,
    val time: Timestamp
): Domain {
    override fun asDto(): ChatDto {
        return ChatDto(
            collection = "Chat",
            documentId = teamId + userId + time,
            data = mapOf(
                "teamId" to teamId,
                "userId" to userId,
                "text" to text,
                "time" to Timestamp.now()
            )
        )
    }
}

data class ChatDto(
    override val collection: String? = null,
    override val documentId: String? = null,
    override val data: Map<String, Any?> = mapOf(
        "teamId" to null,
        "userId" to null,
        "text" to null,
        "time" to null
    )
): Dto {
    override fun asDomain(): Chat {
        return Chat(
            userId = this.data["userId"].toString().orEmpty(),
            teamId = this.data["teamId"].toString().orEmpty(),
            text   = this.data["text"].toString().orEmpty(),
            time   = this.data["time"] as Timestamp
        )
    }
}

data class Device (
    val name: String,
    val fcmToken: String,
    var naverToken: String
): Domain {
    override fun asDto(): DeviceDto {
        return DeviceDto(
            collection = "Device",
            documentId = fcmToken,
            data = mapOf(
                "name" to name,
                "fcmToken" to fcmToken,
                "naverToken" to naverToken
            )
        )
    }
}

data class DeviceDto (
    override val collection: String? = null,
    override val documentId: String? = null,
    override val data: Map<String, Any?> = mapOf(
        "name" to null,
        "fcmToken" to null,
        "naverToken" to null
    )
): Dto {
    override fun asDomain(): Device {
        return Device(
            name = this.data["name"].toString(),
            fcmToken = this.documentId.toString(),
            naverToken = this.data["naverToken"].toString()
        )
    }
}