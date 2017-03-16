package com.ggaier.gamedev.movement

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.TimeUtils
import com.badlogic.gdx.utils.viewport.ExtendViewport

/**
 * Created by ggaier at 16/03/2017 .
 * jwenbo52@gmail.com
 */
private const val WORLD_SIZE = 480f
private const val CIRCLE_RADIUS = WORLD_SIZE / 20
private const val MOVEMENT_DISTANCE = WORLD_SIZE / 4
private const val PERIOD = 2f

class ReciprocatingMotion : ApplicationAdapter() {

    lateinit var mRenderer: ShapeRenderer
    /**
     * 扩展World，但是同时保持World的AspectRatio。
     */
    lateinit var mViewport: ExtendViewport
    var mStartTime: Long = Long.MIN_VALUE

    override fun create() {
        mRenderer = ShapeRenderer()
        mViewport = ExtendViewport(WORLD_SIZE, WORLD_SIZE)
        mStartTime = TimeUtils.nanoTime()
    }

    override fun render() {
        mViewport.apply()

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    override fun dispose() {
        mRenderer.dispose()
    }

    override fun resize(width: Int, height: Int) {
        mViewport.update(width, height, true)
    }


}