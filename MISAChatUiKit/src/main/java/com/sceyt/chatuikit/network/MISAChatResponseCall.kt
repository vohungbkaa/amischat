package com.sceyt.chatuikit.network


import com.sceyt.chatuikit.data.models.MISAChatResponse
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class MISAChatResponseCall<T>(
    private val call: Call<T>,
) : Call<MISAChatResponse<T>> {
    override fun enqueue(callback: Callback<MISAChatResponse<T>>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                val code = response.code()
                val message = response.message()

                val result = if (response.isSuccessful) {
                    MISAChatResponse.Success(body)
                } else {
                    MISAChatResponse.Error(data = body)
                }

                callback.onResponse(this@MISAChatResponseCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                // Tạo một MISAChatResponse.Error với thông tin từ Throwable
                val errorResponse: MISAChatResponse<T> = MISAChatResponse.Error(
                    message = t.message
                )

                val response = Response.success(errorResponse)
                callback.onResponse(this@MISAChatResponseCall, response)
            }
        })
    }

    override fun isExecuted() = call.isExecuted

    override fun clone(): Call<MISAChatResponse<T>> = MISAChatResponseCall(call.clone())

    override fun execute(): Response<MISAChatResponse<T>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun isCanceled() = call.isCanceled

    override fun cancel() = call.cancel()


    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()
}