package com.ggaier.game.libgdxdemo.movement.screensaver

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.ExtendViewport

/**
 * Created by ggaier at 19/03/2017 .
 * jwenbo52@gmail.com
 */
private const val WORLD_SIZE = 480f
private const val BALL_COUNT = 10000

class BallScreen: ScreenAdapter(), InputProcessor {

    lateinit var mRenderer: ShapeRenderer
    lateinit var mViewport: ExtendViewport
    lateinit var mBall: BounceBall
    val mBalls = mutableListOf<BounceBall>()

    override fun show() {
        mRenderer = ShapeRenderer()
        mRenderer.setAutoShapeType(true)
        mViewport = ExtendViewport(WORLD_SIZE, WORLD_SIZE)
        mBall = BounceBall(mViewport)
        for (i in 1..BALL_COUNT) {
            mBalls.add(BounceBall(mViewport))
        }
        Gdx.input.inputProcessor = this
    }

    private fun initBalls() {
        mBall.initBall(mViewport)
        for (ball in mBalls) {
            ball.initBall(mViewport)
        }
    }

    override fun resize(width: Int, height: Int) {
        mViewport.update(width, height, true)
        initBalls()
    }

    /**
     * @param delta 表示的是自上次渲染之后到这次渲染开始的时间。
     *              通常用来重绘游戏世界，来模拟游戏世界中物体的运动。
     */
    override fun render(delta: Float) {
        mViewport.apply()
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mRenderer.projectionMatrix = mViewport.camera.combined
        mRenderer.begin(ShapeRenderer.ShapeType.Filled)
        mBall.update(delta, mViewport)
        mBall.render(mRenderer)
//        for (ball in mBalls) {
//            ball.update(delta, mViewport)
//            ball.render(mRenderer)
//        }
        mRenderer.end()
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun scrolled(amount: Int): Boolean {
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        if (keycode == Input.Keys.SPACE){
            initBalls()
        }
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return false
    }

    override fun keyDown(keycode: Int): Boolean {
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

}