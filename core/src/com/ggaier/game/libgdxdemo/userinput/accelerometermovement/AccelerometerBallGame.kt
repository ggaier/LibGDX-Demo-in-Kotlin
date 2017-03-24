package com.ggaier.game.libgdxdemo.userinput.accelerometermovement

import com.badlogic.gdx.Game

/**
 * Created by ggaier at 24/03/2017 .
 * jwenbo52@gmail.com
 */
class AccelerometerBallGame : Game() {

    override fun create() {
        setScreen(BallScreen())
    }

}