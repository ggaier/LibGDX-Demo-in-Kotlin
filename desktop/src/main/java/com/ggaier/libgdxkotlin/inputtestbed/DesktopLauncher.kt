package com.ggaier.libgdxkotlin.inputtestbed

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.ggaier.gamedev.userinput.inputtestbed.InputTestBed

/**
 * Created by ggaier at 19/03/2017 .
 * jwenbo52@gmail.com
 */
class DesktopLauncher {

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            val config=LwjglApplicationConfiguration()
            LwjglApplication(InputTestBed(),config)
        }
    }

}