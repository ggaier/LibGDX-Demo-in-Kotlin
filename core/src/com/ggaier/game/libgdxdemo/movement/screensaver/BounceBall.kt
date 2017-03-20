package com.ggaier.game.libgdxdemo.movement.screensaver

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import java.util.*

/**
 * Created by ggaier at 19/03/2017 .
 * jwenbo52@gmail.com
 */
private const val RADIUS_FACTOR = 1.0f / 20
private const val KICK_VELOCITY = 500f

class BounceBall(viewport: Viewport) {

    val COLOR: Color = Color.RED
    var mRadius: Float = Float.MIN_VALUE
    lateinit var mPosition: Vector2
    val mVelocity = Vector2(0f, 0f)

    init {
        initBall(viewport)
    }

    fun initBall(viewport: Viewport) {
        mPosition = Vector2(viewport.worldWidth / 2, viewport.worldHeight / 2)
        mRadius = RADIUS_FACTOR * Math.min(viewport.worldWidth, viewport.worldHeight)
        randomKick()
    }

    private fun randomKick() {
        val random = Random()
        val angel = MathUtils.PI2 * random.nextFloat()
        mVelocity.x = KICK_VELOCITY * MathUtils.cos(angel)
        mVelocity.y = KICK_VELOCITY * MathUtils.sin(angel)
    }

    fun update(delta: Float, viewport: Viewport) {
        mPosition.x += delta * mVelocity.x
        mPosition.y += delta * mVelocity.y
        collideWithWalls(mRadius, viewport.worldWidth, viewport.worldHeight)
    }

    fun collideWithWalls(radius: Float, viewportWidth: Float, viewportHeight: Float) {
        if (mPosition.x - radius < 0) {
            mPosition.x = radius
            mVelocity.x = -mVelocity.x
        }

        if (mPosition.x + radius > viewportWidth) {
            mPosition.x = viewportWidth - radius
            mVelocity.x = -mVelocity.x
        }

        if (mPosition.y - radius < 0) {
            mPosition.y = radius
            mVelocity.y = -mVelocity.y
        }

        if (mPosition.y + radius > viewportHeight) {
            mPosition.y = viewportHeight - radius
            mVelocity.y = -mVelocity.y
        }
    }

    fun render(renderer: ShapeRenderer) {
        renderer.set(ShapeRenderer.ShapeType.Filled)
        renderer.color = COLOR
        renderer.circle(mPosition.x, mPosition.y, mRadius)
    }


}