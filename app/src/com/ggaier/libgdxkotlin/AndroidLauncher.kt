package com.ggaier.libgdxkotlin

import android.os.Bundle
import android.widget.Toast
import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.ggaier.gamedev.StartFiled
import com.ggaier.gamedev.dots.ConnectTheDots
import com.ggaier.gamedev.flower.RectangleFlower
import com.ggaier.gamedev.spirals.DrawASpiral

/**
 * Created by ggaier at 27/02/2017 .
 * jwenbo52@Gmail.com
 */
class AndroidLauncher : AndroidApplication() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val flag = intent.getIntExtra(FLAG, FLAG_START_FILED)
        var applicationListener: ApplicationListener? = null
        when (flag) {
            FLAG_START_FILED -> applicationListener = StartFiled()
            FLAG_CONNECT_DOTS -> applicationListener = ConnectTheDots()
            FLAG_SPIRAL -> applicationListener = DrawASpiral()
            FLAG_FLOWER -> applicationListener = RectangleFlower()
            else -> {
                Toast.makeText(this@AndroidLauncher,"Wrong Drawing Type",Toast.LENGTH_LONG).show()
            }
        }
        val androidApplicationConfig = AndroidApplicationConfiguration()
        if (applicationListener != null) {
            initialize(applicationListener, androidApplicationConfig)
        }

    }


}