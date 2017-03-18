package com.ggaier.gamedev.movement.fallingobjects

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import java.util.*

/**
 * Created by ggaier at 18/03/2017 .
 * jwenbo52@gmail.com
 */
private const val RADIUS_RATIO = 0.01f
private const val GRAVITY = -20f
private val COLOR = Color.RED

class Boulder(viewport: Viewport) {

    val mPosition: Vector2 = Vector2()
    val mVelocity: Vector2 = Vector2(0f, 0f)
    var mRadius: Float = Float.MIN_VALUE

    init {
        mRadius = viewport.worldWidth * RADIUS_RATIO
        mPosition.y = viewport.worldHeight + mRadius
        mPosition.x = Random().nextFloat() * (viewport.worldWidth - 2 * mRadius) + mRadius
    }


    /**
     * @param delta 表示的是
     */
    fun update(delta: Float) {
        mVelocity.y += delta * GRAVITY
        mPosition.x += delta * mVelocity.x
        mPosition.y += delta * mVelocity.y
    }

    fun isBelowScreen(): Boolean = mPosition.x < -mRadius

    fun render(render: ShapeRenderer) {
        render.set(ShapeRenderer.ShapeType.Filled)
        render.color= COLOR
        render.circle(mPosition.x, mPosition.y, mRadius)
    }



}