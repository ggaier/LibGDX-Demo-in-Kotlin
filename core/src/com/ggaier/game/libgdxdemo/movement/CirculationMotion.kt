package com.ggaier.game.libgdxdemo.movement

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
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
        mViewport.update(width, height, true)
    }

    override fun dispose() {
        mRenderer.dispose()
    }

    override fun render() {
        mViewport.apply()

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mRenderer.projectionMatrix = mViewport.camera.combined
        mRenderer.begin(ShapeRenderer.ShapeType.Filled)

        val elapsedNanoseconds = TimeUtils.nanoTime() - mInitialTime
        val elapsedSeconds = MathUtils.nanoToSec * elapsedNanoseconds
        val elapsedPeriod = elapsedSeconds / PERIOD
        val cyclePosition = elapsedPeriod % 1

        val x = WORLD_SIZE / 2 + MOVEMENT_RADIUS * MathUtils.cos(
                MathUtils.PI2 * cyclePosition)
        val y = WORLD_SIZE / 2 + MOVEMENT_RADIUS * MathUtils.sin(
                MathUtils.PI2 * cyclePosition)

        mRenderer.circle(x, y, CIRCLE_RADIUS)

        drawFancyCircles(mRenderer, elapsedPeriod, 20)

        mRenderer.end()
    }

    private fun drawFancyCircles(renderer: ShapeRenderer, elapsedPeriod: Float,
                                 circleCount: Int) {
        for (i in 1..circleCount) {
            val centerX = WORLD_SIZE / 2 + WORLD_SIZE / 4 * MathUtils.cos(
                    MathUtils.PI2 * i / circleCount)
            val centerY = WORLD_SIZE / 2 + WORLD_SIZE / 4 * MathUtils.sin(
                    MathUtils.PI2 * i / circleCount)
            val x = centerX + WORLD_SIZE / 5 * MathUtils.cos(
                    MathUtils.PI2 * (elapsedPeriod * i / circleCount))
            val y = centerY + WORLD_SIZE / 5 * MathUtils.sin(
                    MathUtils.PI2 * (elapsedPeriod * i / circleCount))
            renderer.circle(x, y, 10f)
        }
    }

}