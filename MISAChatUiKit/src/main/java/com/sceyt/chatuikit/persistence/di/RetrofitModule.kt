
package com.sceyt.chatuikit.persistence.di

import com.google.gson.GsonBuilder
import com.sceyt.chatuikit.network.MISAChatCallAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 60L
private const val WRITE_TIMEOUT = 60L
private const val READ_TIMEOUT = 60L

val retrofitModule = module {
    single { Cache(androidApplication().cacheDir, 10L * 1024 * 1024) }
    single { GsonBuilder().create() }
    single { retrofitHttpClient() }
    single { retrofitBuilder() }
    single {
        Interceptor { chain ->
            chain.proceed(chain.request().newBuilder().apply {
                header("Accept", "application/json")
                addHeader("Cookie", "x-culture=vi; x-culture-custom=vi; x-deviceid=57e1fbb3-4bc3-4803-9628-b94d0f18eb01; _gid=GA1.2.1735910556.1738760423; _ga=GA1.1.274140361.1735964342; x-sessionid=56c33c4778ec4b3a92ba9fdb7830a6d459c053585c334fc9b0a547be2c016a9c; x-tenantid=bBtomi%2BoAvG96Hcgu5lXamIioSCz%2FchFsh3uiKngd8%2FA6iplZNdxOP8%2FneTpY6LI; x-login-from=basic; _ga_4N8J1W6EBF=GS1.1.1738760423.6.1.1738760494.0.0.0; _ga_6NQ98LXLDM=GS1.1.1738760428.6.1.1738761072.0.0.0; x-lastapp=Chat%3B%2Fchat%2F")
            }.build())
        }
    }
}

private fun Scope.retrofitBuilder(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://misajsc.amis.vn/")
        .addConverterFactory(GsonConverterFactory.create(get()))
        .addCallAdapterFactory(MISAChatCallAdapterFactory())
        .client(get())
        .build()
}

private fun Scope.retrofitHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        cache(get())
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        retryOnConnectionFailure(true)
        addInterceptor(get<Interceptor>())
        addInterceptor(HttpLoggingInterceptor().apply {
            /*level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.HEADERS
            }
            else {
                HttpLoggingInterceptor.Level.NONE
            }*/
        })
    }.build()
}