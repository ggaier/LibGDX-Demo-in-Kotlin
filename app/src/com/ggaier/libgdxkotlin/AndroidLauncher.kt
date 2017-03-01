package com.ggaier.libgdxkotlin

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.ggaier.gamedev.StartFiled

/**
 * Created by ggaier at 27/02/2017 .
 * jwenbo52@Gmail.com
 */
class AndroidLauncher : AndroidApplication() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val androidApplicationConfig= AndroidApplicationConfiguration()
        initialize(StartFiled(),androidApplicationConfig)
    }


}