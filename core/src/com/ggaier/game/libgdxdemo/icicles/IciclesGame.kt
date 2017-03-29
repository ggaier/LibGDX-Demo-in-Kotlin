package com.ggaier.game.libgdxdemo.icicles

import com.badlogic.gdx.Game

/**
 * Created by ggaier at 25/03/2017 .
 * jwenbo52@gmail.com
 */
class IciclesGame : Game() {
    override fun create() {
        showIciclesScreen(Difficulty.MEDIUM)
    }

    fun showDifficultyScreen(){
        setScreen(DifficultyScreen(this))
    }

    fun showIciclesScreen(difficulty: Difficulty){
        setScreen(IcicleScreen(difficulty,this))
    }

}