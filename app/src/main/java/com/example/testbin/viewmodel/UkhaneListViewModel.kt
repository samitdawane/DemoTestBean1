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

    init {
        viewModelScope.launch {
            val type = savedStateHandle.get<String>("category") ?: "motivation"
            ukhaneRepository.getUkhaneByType(type)

        }
    }


}