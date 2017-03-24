package com.ggaier.game.libgdxdemo.userinput.accelerometermovement

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
 * Created by ggaier at 24/03/2017 .
 * jwenbo52@gmail.com
 */
private val COLOR = Color.RED
private const val DRAG = 0.5f
private const val RADIUS_FACTOR = 1.0f / 20
private const val RADIUS_GROWTH_RATE = 1.5f
private const val MIN_RADIUS_MULTIPLIER = 0.1f
private const val ACCELERATION = 500.0f
private const val MAX_SPEED = 4000.0f
private const val ACCELEROMETER_SENSITIVITY = 0.5f
private const val ACCELERATION_OF_GRAVITY = 9.8f
private const val KICK_VELOCITY = 500.0f

class BounceBall(val viewport: Viewport) : InputAdapter() {

    var mLastKick: Long = Long.MIN_VALUE
    var mBaseRadius: Float = Float.MIN_VALUE
    var mRadiusMultiplier: Float = Float.MIN_VALUE
    lateinit var mPosition: Vector2
    lateinit var mVelocity: Vector2

    lateinit var mFlickStart: Vector2
    var mFlicking: Boolean = false
    lateinit var mTargetPosition: Vector2
    var mFollowing: Boolean = false

    init {
        init()
    }

    fun init() {
        mPosition = Vector2(viewport.worldWidth / 2, viewport.worldHeight / 2)
        mVelocity = Vector2()
        mBaseRadius = RADIUS_FACTOR * Math.min(viewport.worldWidth, viewport.worldHeight)
        mRadiusMultiplier = 1f
    }

    private fun randomKick() {
        val random = Random()
        val angle = MathUtils.PI2 * random.nextFloat()
        mVelocity.x = KICK_VELOCITY * MathUtils.cos(angle)
        mVelocity.y = KICK_VELOCITY * MathUtils.sin(angle)
    }

    fun update(delta: Float, viewport: Viewport) {
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            mRadiusMultiplier += delta * RADIUS_GROWTH_RATE
        }

        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            mRadiusMultiplier -= delta * RADIUS_GROWTH_RATE
            mRadiusMultiplier = Math.max(mRadiusMultiplier,
                    MIN_RADIUS_MULTIPLIER)
        }

        if (mFollowing) {
            mVelocity.x = 2 * (mTargetPosition.x - mPosition.x)
            mVelocity.y = 2 * (mTargetPosition.y - mPosition.y)
        }

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

        val xAxis = -Gdx.input.accelerometerY
        val yAxis = Gdx.input.accelerometerX

        val accelerationX = -ACCELERATION * xAxis / (ACCELEROMETER_SENSITIVITY * ACCELERATION_OF_GRAVITY)
        val accelerationY = -ACCELERATION * yAxis / (ACCELEROMETER_SENSITIVITY * ACCELERATION_OF_GRAVITY)

        mVelocity.x += delta * accelerationX
        mVelocity.y += delta * accelerationY

        //限制球的运行速度不超过最小值和最大值
        mVelocity.clamp(0f, MAX_SPEED)

        mVelocity.x -= delta * DRAG * mVelocity.x
        mVelocity.y -= delta * DRAG * mVelocity.y

        mPosition.x += delta * mVelocity.x
        mPosition.y += delta * mVelocity.y

        collideWithWalls(mBaseRadius * mRadiusMultiplier, viewport.worldWidth, viewport.worldHeight)
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
        renderer.circle(mPosition.x, mPosition.y, mBaseRadius)
    }

    override fun keyDown(keycode: Int): Boolean {
        if (keycode == Input.Keys.SPACE) {
            randomKick()
        }
        if (keycode == Input.Keys.R) {
            init()
        }
        return true
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val worldClick = viewport.unproject(Vector2(screenX.toFloat(), screenY.toFloat()))
        if (worldClick.dst(mPosition) < mBaseRadius * mRadiusMultiplier) {
            Gdx.app.log("ball", "Click in the ball, start flick. ")
            mFlicking = true
            mFlickStart = worldClick
        } else {
            mTargetPosition = worldClick
            mFollowing = true
        }
        return true
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        mTargetPosition = viewport.unproject(Vector2(screenX.toFloat(), screenY.toFloat()))
        return true
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if (mFlicking) {
            mFlicking = false
            val flickEnd = viewport.unproject(Vector2(screenX.toFloat(), screenY.toFloat()))
            mVelocity.x += 3 * (flickEnd.x - mFlickStart.x)
            mVelocity.y += 3 * (flickEnd.y - mFlickStart.y)
            Gdx.app.log("Ball", " end flick")
        }
        mFollowing = false
        return true
    }

}
