package com.ggaier.game.libgdxdemo.movement.applicationadaptertogame

import com.badlogic.gdx.Game

/**
 * Created by ggaier at 16/03/2017 .
 * jwenbo52@gmail.com
 */
class MyGame : Game() {

    override fun create() {
        setScreen(MyScreen())
    }

}