package com.sceyt.chatuikit.network

import com.sceyt.chatuikit.data.models.MISAChatResponse
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class MISAChatCallAdapterFactory : CallAdapter.Factory() {
    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        // Kiểm tra nếu kiểu trả về là một `suspend` function
        if (getRawType(returnType) != retrofit2.Call::class.java) {
            throw IllegalArgumentException("Return type must be a Call<T>")
        }

        // Lấy generic type bên trong `Call`
        val callType = getParameterUpperBound(0, returnType as ParameterizedType)

        // Kiểm tra nếu generic type bên trong là `MISAChatResponse`
        val rawType = getRawType(callType)
        if (rawType != MISAChatResponse::class.java) return null

        // Lấy type bên trong `MISAChatResponse<T>`
        if (callType !is ParameterizedType) {
            throw IllegalArgumentException("MISAChatResponse must be parameterized")
        }

        val responseType = getParameterUpperBound(0, callType)
        return MISAChatCallAdapter<Any>(responseType)
    }
}
