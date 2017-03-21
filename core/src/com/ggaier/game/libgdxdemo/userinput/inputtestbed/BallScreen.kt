package com.ggaier.game.libgdxdemo.userinput.inputtestbed

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.ExtendViewport

/**
 * Created by ggaier at 21/03/2017 .
 * jwenbo52@gmail.com
 */
private const val WORLD_SIZE = 480.0f

class BallScreen : ScreenAdapter() {

    lateinit var mRenderer: ShapeRenderer
    lateinit var mViewport: ExtendViewport
    lateinit var mBall: BouncingBall

    override fun show() {
        mRenderer = ShapeRenderer()
        mRenderer.setAutoShapeType(true)
        mViewport = ExtendViewport(WORLD_SIZE, WORLD_SIZE)
        mBall = BouncingBall(mViewport)
        Gdx.input.inputProcessor = mBall
    }

    override fun resize(width: Int, height: Int) {
        mViewport.update(width, height, true)
        mBall.init(mViewport)
    }

    override fun dispose() {
        mRenderer.dispose()
    }

    override fun render(delta: Float) {
        mViewport.apply()
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mRenderer.projectionMatrix = mViewport.camera.combined
        mBall.update(delta, mViewport)
        mRenderer.begin(ShapeRenderer.ShapeType.Filled)
        mBall.render(mRenderer)
        mRenderer.end()
    }


}