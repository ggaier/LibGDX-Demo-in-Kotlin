package com.ggaier.game.libgdxdemo.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.ggaier.game.libgdxdemo.GdxGame

object DesktopLauncher {

    @JvmStatic fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        LwjglApplication(GdxGame(), config)
    }

}
