package com.example.testbin.repository

import com.example.testbin.api.UkhaneApi
import com.example.testbin.model.Ukhane
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UkhaneRepository @Inject constructor(val ukhaneApi: UkhaneApi) {

    private val _ukhaneTypes = MutableStateFlow<List<String>>(emptyList())
    val ukhaneTypes : StateFlow<List<String>>
    get() = _ukhaneTypes

    suspend fun getAllUkhaneTypes(){
        val result = ukhaneApi.getUkhaneTypes()
        if(result.isSuccessful && result.body() != null){
            _ukhaneTypes.emit(result.body()!!)
        }
    }

    private val _UkhaneList = MutableStateFlow<List<Ukhane>>(emptyList())
    val UkhaneList : StateFlow<List<Ukhane>>
    get() = _UkhaneList

    suspend fun getUkhaneByType(type:String){

        val result = ukhaneApi.getUkhaneByType("UkhaneList[?(@.type==\"$type\")]")
        if (result.isSuccessful && result.body()!= null){
            _UkhaneList.emit(result.body()!!)
        }
    }
}