package com.misa.vn.amischat

import android.app.Application
import com.sceyt.chatuikit.SceytChatUIKit
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.UUID

class AmisChatApplication : Application()  {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AmisChatApplication)
        }

        SceytChatUIKit.initialize(
            appContext = this,
            apiUrl = "https://us-ohio-api.sceyt.com",
            appId = "8lwox2ge93",
            clientId = UUID.randomUUID().toString(),
            enableDatabase = true
        )
    }
}