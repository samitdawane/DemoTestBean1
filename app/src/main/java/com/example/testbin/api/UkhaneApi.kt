package com.example.testbin.api

import com.example.testbin.model.Ukhane
import com.example.testbin.model.UkhaneResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface UkhaneApi {

    @GET("/v3/b/657aae26266cfc3fde68a131?meta=false")
    @Headers("X-JSON-Path:UkhaneList..type")
    suspend fun getUkhaneTypes() : Response<List<String>>

    @GET("/v3/b/657aae26266cfc3fde68a131?meta=false")
    suspend fun getUkhaneByType(@Header("X-JSON-Path") type: String) :  Response<List<Ukhane>>



}