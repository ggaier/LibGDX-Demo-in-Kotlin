package com.ggaier.gamedev.orthographiccamera

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.TimeUtils

/**
 * Created by ggaier at 06/03/2017 .
 * jwenbo52@gmail.com
 */
const val BALL_RADIUS = 20f
const val PERIOD = 2000
const val X_AMPLITUDE = 40
const val Y_AMPLITUDE = 20
const val X_CENTER = 100f
const val Y_CENTER = 100f

class OrthographicCamera : ApplicationAdapter() {

    lateinit var mShapeRenderer: ShapeRenderer
    var mTimeCreated: Long = 0
        private set
    lateinit var mCamera: OrthographicCamera

    override fun create() {
        mShapeRenderer = ShapeRenderer()
        mTimeCreated = TimeUtils.millis()
        mCamera = OrthographicCamera()
        mCamera.position.set(X_CENTER, Y_CENTER, 0f)
    }

    override fun render() {
        Gdx.gl.glClearColor(0f,0f,0f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mCamera.update()

        mShapeRenderer.projectionMatrix=mCamera.combined
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        val interval=TimeUtils.timeSinceMillis(mTimeCreated)
        val x= X_CENTER+ X_AMPLITUDE+MathUtils.sin(MathUtils.PI2*interval/ PERIOD)
        val y= Y_CENTER+ Y_AMPLITUDE+MathUtils.sin(2*MathUtils.PI2*interval/ PERIOD)
        mShapeRenderer.circle(x,y, BALL_RADIUS)
        mShapeRenderer.end()

    }

    override fun resize(width: Int, height: Int) {
        val aspectRatio = 1.0f * width / height
        mCamera.viewportHeight = 2 * (Y_AMPLITUDE + BALL_RADIUS)
        mCamera.viewportWidth = aspectRatio * mCamera.viewportHeight
    }

    override fun dispose() {
        super.dispose()
        mShapeRenderer.dispose()
    }

}