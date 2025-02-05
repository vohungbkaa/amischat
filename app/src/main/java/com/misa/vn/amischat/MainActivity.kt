package com.misa.vn.amischat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sceyt.chatuikit.presentation.components.channel_list.channels.ChannelListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, ChannelListFragment())
            .commit()
    }

}