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
class UkhaneListViewModel @Inject constructor(val ukhaneRepository: UkhaneRepository,
private val savedStateHandle: SavedStateHandle) : ViewModel(){



    val UkhaneList : StateFlow<List<Ukhane>>
    get() = ukhaneRepository.UkhaneList


    val _ukhana = MutableStateFlow<Ukhane?>(null)
    val ukhanaToDisplay : StateFlow <Ukhane?>
        get() = _ukhana

    var curUkhanaId = 0
    init {
        viewModelScope.launch {
            ukhaneRepository.getUkhaneByType()
            //getUkhanaById(curUkhanaId)
           // _ukhana.value = UkhaneList.value.get(0)
           // var aa = getUkhanaById(1)
           // _ukhana.emit(aa)
        }
    }

    suspend fun getUkhanaById(id : Int) : Ukhane{
        var mData = Ukhane(-1,"Welcome","Welcome")
       for (mUkhana in UkhaneList.value){
           if(mUkhana.id == id){
               mData = mUkhana
               break
           }
       }
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
        Log.e(">>>","Ukhana>>>>"+mData.ukhana)
        Log.e(">>>","Ukhana id>>>>"+mData.id)
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
        Log.e(">>>","Ukhana>>>>"+mData.ukhana)
        Log.e(">>>","Ukhana id>>>>"+mData.id)
        _ukhana.value = mData
        curUkhanaId = mId
        return mData;
    }
}