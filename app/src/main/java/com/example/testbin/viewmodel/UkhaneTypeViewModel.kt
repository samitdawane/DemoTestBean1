package com.example.testbin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbin.model.Ukhane
import com.example.testbin.repository.UkhaneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UkhaneTypeViewModel @Inject constructor(val ukhaneRepository: UkhaneRepository) : ViewModel() {


    val ukhaneTypeList : StateFlow<List<String>>
    get() = ukhaneRepository.ukhaneTypes


    init {
        viewModelScope.launch{
            ukhaneRepository.getAllUkhaneTypes()
        }
    }



}