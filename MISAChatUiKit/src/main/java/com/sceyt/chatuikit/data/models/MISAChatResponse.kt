package com.sceyt.chatuikit.data.models

sealed class MISAChatResponse<T>(
    val data: T? = null,
    val message: String? = null,
    val code: Int? = null
) {
    class Success<T>(data: T?) : MISAChatResponse<T>(data)
    class Error<T>(message: String? = null, code: Int? = null, data: T? = null) :
        MISAChatResponse<T>(message = message, code = code, data = data)
}
