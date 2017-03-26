package com.ggaier.game.libgdxdemo.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.ggaier.game.libgdxdemo.icicles.IciclesGame

object DesktopLauncher {

    @JvmStatic fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        LwjglApplication(IciclesGame(), config)
    }

}
