package com.ggaier.game.libgdxdemo.movement.screensaver

import com.badlogic.gdx.Game

/**
 * Created by ggaier at 19/03/2017 .
 * jwenbo52@gmail.com
 */
class ScreenSaver : Game() {
    override fun create() {
        setScreen(BallScreen())
    }

}