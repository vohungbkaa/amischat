package com.sceyt.chatuikit.network

import com.sceyt.chatuikit.data.models.MISAChatResponse
import java.lang.reflect.Type
import retrofit2.Call
import retrofit2.CallAdapter

class MISAChatCallAdapter<T>(
    private val responseType: Type
) : CallAdapter<T, Call<MISAChatResponse<T>>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<T>): Call<MISAChatResponse<T>> {
        return MISAChatResponseCall(call)
    }
}