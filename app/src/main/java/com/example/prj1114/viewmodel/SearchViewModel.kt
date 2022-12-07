package com.example.prj1114.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prj1114.common.CurrentInfo
import com.example.prj1114.data.*
import com.example.prj1114.util.NaverLogin
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class SearchViewModel: ViewModel() {

    /** dependencies */
    private val naver = NaverLogin()
    private val job = viewModelScope
    private val repository = BaseRepository()
    private val repository2 = TeamRepository()
    private val retrofit = RetrofitClient.getXMLInstance()
    private val jusoService = retrofit.create(JusoService::class.java)

//    lateinit var currentUser: String
//    lateinit var currentTeam: String
//    init {
//        job.launch { currentUser = naver.getCurrentUser() }
//    }

    /** livedata */
    val now = MyTimestamp()

    private var _start = MutableLiveData<Juso>()
    private var _end = MutableLiveData<Juso>()
    private var _keyword = MutableLiveData<String>()
    private var _date = MutableLiveData(Triple(now.year, now.month, now.day))
    private var _time = MutableLiveData(Pair(now.hour, now.minute))
    private var _searchingJusoOf = MutableLiveData<String>()
    private var _searchingTeamOf = MutableLiveData<String>()

    val start: LiveData<Juso> get() = _start
    val end: LiveData<Juso> get() = _end
    val keyword: LiveData<String> get() = _keyword
    val date: LiveData<Triple<Int, Int, Int>> get() = _date
    val time: LiveData<Pair<Int, Int>> get() = _time
    val searchingJusoOf: LiveData<String> get() = _searchingJusoOf
    val searchingTeamOf: LiveData<String> get() = _searchingTeamOf




    fun setKeyword(km: String) {
        _keyword.value = km
    }

    /** set date value */
    fun setDate(year: Int, month: Int, day: Int) {
        _date.value = Triple(year, month, day)
    }

    /** set time value */
    fun setTime(hour: Int, minute: Int){
        _time.value = Pair(hour, minute)
    }

    fun setJuso(juso: Juso) {
        when(searchingJusoOf.value) {
            "start" -> _start.value = juso
            "end" -> _end.value = juso
        }
    }

    fun setSearchingJusoOf(selector: String) {
        when(selector) {
            "start" -> _searchingJusoOf.value = "start"
            "end" -> _searchingJusoOf.value = "end"
        }
    }

    fun setSearchingTeamOf(selector: String) {
        when(selector) {
            "teamId" -> _searchingTeamOf.value = "teamId"
        }
    }

    /** get selected date string */
    fun getDate(): String? {
        return date.value?.let {
            "${it.second + 1}월 ${it.third}일"
        }
    }

    /** get selected time string */
    fun getTime(): String? {
        return time.value?.let {
            "${it.first}시 ${it.second}분"
        }
    }

    fun getDateTime(): Long? {
        return date.value?.let { date ->
            time.value?.let { time ->
                MyTimestamp(
                    date.first,
                    date.second,
                    date.third,
                    time.first,
                    time.second,
                    0
                ).asTimeMillis()
            }
        }
    }

    fun getStart(): String? {
        return start.value?.asString()
    }

    fun getEnd(): String? {
        return end.value?.asString()
    }

    fun getKeyword(): String {
        return keyword.value!!.toString()
    }

    suspend fun saveTeam() {
        return repository.create(Team(
            "sampleTeamId",
            getDateTime()!!,
            start.value!!,
            end.value!!,
            0,
            4,
            1
        ).asDto()
        )
    }

    /** get Team data from db */
    suspend fun getTeam(): ArrayList<Team> {
        return repository2.get(Pair("time", getDateTime()!!))
    }

//    suspend fun autocomplete(): ArrayList<Juso> {
//        val kw = keyword.value.toString()
//        val rtn = arrayListOf<Juso>()
//        return suspendCancellableCoroutine { continuation ->
//            jusoService.getJuso(kw, JusoInit.confmKey)
//                .enqueue(object : Callback<SearchJusoDto> {
//                    override fun onFailure(call: Call<SearchJusoDto>, t: Throwable) {
//                        Log.w("APPLE/", "error occurred with api")
//                        continuation.resumeWithException(t)
//                    }
//
//                    override fun onResponse(
//                        call: Call<SearchJusoDto>,
//                        response: Response<SearchJusoDto>
//                    ) {
//                        response.body()?.let {
//                            it.juso.forEach { juso ->
//                                rtn.add(juso)
//                            }
//                        }
//                        continuation.resume(rtn)
//                    }
//                })
//        }
//
//    }
}
