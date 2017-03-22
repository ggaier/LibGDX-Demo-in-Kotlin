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
private const val FOLLOW_MULTIPLIER = 2.0f

class BouncingBall(val viewport: Viewport) : InputAdapter() {

    val COLOR: Color = Color.RED
    var mRadiusMultiplier = Float.MIN_VALUE
    var mBaseRadius = Float.MIN_VALUE
    lateinit var mPosition: Vector2
    lateinit var mVelocity: Vector2

    lateinit var mFlickStart: Vector2
    var mFlicking: Boolean = false
    lateinit var mTargetPosition: Vector2
    var mFollowing: Boolean = false

    init {
        init(viewport)
    }

    fun init(viewport: Viewport) {
        mPosition = Vector2(viewport.worldWidth / 2, viewport.worldHeight / 2)
        mVelocity = Vector2()
        mRadiusMultiplier = 1f
        mBaseRadius = BASE_RADIUS * mRadiusMultiplier
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
            mRadiusMultiplier = Math.max(mRadiusMultiplier, MIN_RADIUS_MULTIPLIER)
        }

        if (mFollowing) {
            val followVector = Vector2(mTargetPosition.x - mPosition.x,
                    mTargetPosition.y - mPosition.y)
            mVelocity.x = FOLLOW_MULTIPLIER * followVector.x
            mVelocity.y = FOLLOW_MULTIPLIER * followVector.y
        }

        mBaseRadius = mRadiusMultiplier * BASE_RADIUS
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

        collideWithWalls(mBaseRadius, viewport.worldWidth, viewport.worldHeight)
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
        } else if (keycode == Input.Keys.R) {
            init(viewport)
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
        if (mFollowing) {
            mTargetPosition = viewport.unproject(Vector2(screenX.toFloat(), screenY.toFloat()))
        }
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