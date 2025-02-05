
package com.sceyt.chatuikit.persistence.di

import com.sceyt.chatuikit.data.apis.ChannelServices
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    factory { get<Retrofit>().create(ChannelServices::class.java) }
}