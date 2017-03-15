package com.ggaier.gamedev.movement

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.TimeUtils
import com.badlogic.gdx.utils.viewport.FitViewport

/**
 * Created by ggaier at 15/03/2017 .
 * jwenbo52@gmail.com
 */
private const val WORLD_SIZE = 480f
private const val CIRCLE_RADIUS = WORLD_SIZE / 20
private const val MOVEMENT_RADIUS = WORLD_SIZE / 4
private const val PERIOD = 1.0f

class CirculationMotion : ApplicationAdapter() {

    lateinit var mRenderer: ShapeRenderer
    lateinit var mViewport: FitViewport
    var mInitialTime: Long = 0

    override fun create() {
        mRenderer = ShapeRenderer()
        mViewport = FitViewport(WORLD_SIZE, WORLD_SIZE)
        mInitialTime = TimeUtils.nanoTime()
    }

    override fun resize(width: Int, height: Int) {
        mViewport.update(width,height,true)
    }

    override fun dispose() {
        mRenderer.dispose()
    }

    override fun render() {
        Gdx.gl.glClearColor(0f,0f,0f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        
    }

}