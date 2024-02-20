package com.example.testbin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testbin.repository.UkhaneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UkhanaViewModel @Inject constructor(val ukhaneRepository: UkhaneRepository) : ViewModel(){

    init {
        viewModelScope.launch {

        }
    }
}