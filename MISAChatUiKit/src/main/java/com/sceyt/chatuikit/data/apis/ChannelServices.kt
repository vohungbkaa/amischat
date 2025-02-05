package com.sceyt.chatuikit.data.apis

import com.sceyt.chatuikit.data.models.MISAChatResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface ChannelServices {

    @GET("users")
    suspend fun getChannels(): MISAChatResponse<Any>

    @GET("chat/api/business/v1/conversations/me")
    @Headers(
        "Accept: application/json, text/plain, */*",
        "Accept-Language: en-US,en;q=0.9,vi;q=0.8",
        "Access-Control-Allow-Origin: *",
        "Connection: keep-alive",
        "Referer: https://misajsc.amis.vn/chat/cv/67a2d0d24901fd5629d4950d",
        "Response-Type: json",
        "Sec-Fetch-Dest: empty",
        "Sec-Fetch-Mode: cors",
        "Sec-Fetch-Site: same-origin",
        "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36",
        "sec-ch-ua: \"Not A(Brand\";v=\"8\", \"Chromium\";v=\"132\", \"Google Chrome\";v=\"132\"",
        "sec-ch-ua-mobile: ?0",
        "sec-ch-ua-platform: \"macOS\""
    )
    fun getConversations(): MISAChatResponse<Any>
}
