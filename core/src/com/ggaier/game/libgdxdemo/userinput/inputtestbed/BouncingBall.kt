package com.ggaier.game.libgdxdemo.userinput.inputtestbed

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import java.util.*

/**
 * Created by ggaier at 21/03/2017 .
 * jwenbo52@gmail.com
 */
private const val DRAG = 1.0f
private const val BASE_RADIUS = 20.0f
private const val RADIUS_GROWTH_RATE = 1.5f
private const val MIN_RADIUS_MULTIPLIER = 0.1f
private const val ACCELERATION = 500.0f
private const val MAX_SPEED = 1000.0f
private const val KICK_VELOCITY = 500.0f

class BouncingBall(viewport: Viewport) : InputAdapter() {

    val COLOR = Color.RED
    var mRadiusMutiplier = Float.MIN_VALUE
    var mRadius = Float.MIN_VALUE
    lateinit var mPosition: Vector2
    lateinit var mVelocity: Vector2

    init {
        init(viewport)
    }

    fun init(viewport: Viewport) {
        mPosition = Vector2(viewport.worldWidth / 2, viewport.worldHeight / 2)
        mVelocity = Vector2()
        mRadiusMutiplier = 1f
        mRadius = BASE_RADIUS * mRadiusMutiplier
        mRadiusMutiplier = 1f
    }

    private fun randomKick() {
        val random = Random()
        val angle = MathUtils.PI2 * random.nextFloat()
        mVelocity.x = KICK_VELOCITY * MathUtils.cos(angle)
        mVelocity.y = KICK_VELOCITY * MathUtils.sin(angle)
    }

    fun update(delta: Float, viewport: Viewport) {
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            mRadiusMutiplier += delta * RADIUS_GROWTH_RATE
        }

        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            mRadiusMutiplier -= delta * RADIUS_GROWTH_RATE
            mRadiusMutiplier = Math.max(mRadiusMutiplier, MIN_RADIUS_MULTIPLIER)
        }

        mRadius = mRadiusMutiplier * BASE_RADIUS
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            mVelocity.x -= delta * ACCELERATION
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            mVelocity.x += delta * ACCELERATION
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            mVelocity.y += delta * ACCELERATION
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            mVelocity.y -= delta * ACCELERATION
        }

        //限制球的运行速度不超过最小值和最大值
        mVelocity.clamp(0f, MAX_SPEED)

        mVelocity.x -= delta * DRAG * mVelocity.x
        mVelocity.y -= delta * DRAG * mVelocity.y

        mPosition.x += delta * mVelocity.x
        mPosition.y += delta * mVelocity.y

        collideWithWalls(mRadius, viewport.worldWidth, viewport.worldHeight)
    }

    private fun collideWithWalls(radius: Float, viewportWidth: Float, viewportHeight: Float) {
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

    override fun keyDown(keycode: Int): Boolean {
        if(keycode==Input.Keys.SPACE){
            randomKick()
        }
        return true
    }


}