package com.example.prj1114.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.prj1114.common.CurrentInfo
import com.example.prj1114.data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class DetailViewModel : ViewModel() {
    private val repository = TeamRepository()

    /** 데이터 대기중을 나타내는 liveData */
    private val _isAwait = MutableLiveData(true)
    val isAwait: LiveData<Boolean> get() = _isAwait

    /** DetailFragment 화면에 뿌려줄 데이터 */
    private val sampleTeamId = "sampleTeamId"
    lateinit var sampleTeam: Team
    lateinit var max: String
    lateinit var time: String
    lateinit var start: String
    lateinit var end: String

    private fun setAwait(prop: Boolean) {
        _isAwait.value = prop
    }

    suspend fun getTargetTeam() {
        Log.d("APPLE", "coroutine launch")
        repository.get(sampleTeamId)?.let {
            Log.d("APPLE", "$it")
            time = convertLongToDate(it.time)
            max = convertMaxString(it.max, it.curr)
            start = it.start["roadAddr"] as String
            end = it.end["roadAddr"] as String
        }
        setAwait(false)
    }
    private fun convertMaxString(max: Int?, curr: Int?): String {
        return "$curr / $max"
    }
    private fun convertLongToDate(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("MM.dd HH:mm", Locale.KOREA)
        return format.format(date)
    }
}