package com.ggaier.game.libgdxdemo.userinput.inputtestbed

import com.badlogic.gdx.Game

/**
 * Created by ggaier at 19/03/2017 .
 * jwenbo52@gmail.com
 */
class InputTestBed: Game(){

    override fun create() {
        setScreen(BallScreen())
    }

}