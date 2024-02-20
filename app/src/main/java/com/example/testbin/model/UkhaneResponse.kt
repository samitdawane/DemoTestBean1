package com.example.testbin.model

import com.google.gson.annotations.SerializedName

data class UkhaneResponse(@SerializedName("UkhaneList") val ukhaneList : List<Ukhane>)
