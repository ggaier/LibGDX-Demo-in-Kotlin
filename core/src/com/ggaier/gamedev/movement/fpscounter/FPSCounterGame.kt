package com.ggaier.gamedev.movement.fpscounter

import com.badlogic.gdx.Game

/**
 * Created by ggaier at 16/03/2017 .
 * jwenbo52@gmail.com
 */
class FPSCounterGame :Game(){

    override fun create() {
        setScreen(FPSCounterScreen())
    }

}