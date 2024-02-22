package com.example.testbin.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbin.model.Ukhane
import com.example.testbin.repository.UkhaneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UkhanaViewModel @Inject constructor(val ukhaneRepository: UkhaneRepository,savedStateHandle: SavedStateHandle) : ViewModel(){

    private val argument = checkNotNull(savedStateHandle.get<Int>("ukhanaId"))
    var curUkhanaId = argument
    val UkhaneList : StateFlow<List<Ukhane>>
        get() = ukhaneRepository.UkhaneList



    val _ukhana = MutableStateFlow<Ukhane?>(null)
    val ukhanaToDisplay : StateFlow<Ukhane?>
        get() = _ukhana


    init {
        Log.e(">>>>",">>>>arg>>>>"+argument)
        viewModelScope.launch {
            val type = savedStateHandle.get<String>("category") ?: "motivation"
            ukhaneRepository.getUkhaneByType(type)
            getUkhanaById(curUkhanaId)
        }
    }


    suspend fun getUkhanaById(id : Int) : Ukhane{
        var mData = Ukhane(-1,"Welcome","Welcome")
        mData = UkhaneList.value[id]
        _ukhana.emit(mData)
        return mData;
    }

    suspend fun getNextUkhana(id : Int) : Ukhane{
        var mId = id
        if(mId == UkhaneList.value.size-1){
            mId = 0
        }else{
            mId++
        }
        var mData = UkhaneList.value.get(mId)
        _ukhana.value = mData
        curUkhanaId = mId
        return mData;
    }

    suspend fun getPreviousUkhana(id : Int) : Ukhane{
        var mId = id
        if(mId==0){
            mId = UkhaneList.value.size-1
        }else{
            mId--
        }
        var mData = UkhaneList.value.get(mId)
        _ukhana.value = mData
        curUkhanaId = mId
        return mData;
    }
}