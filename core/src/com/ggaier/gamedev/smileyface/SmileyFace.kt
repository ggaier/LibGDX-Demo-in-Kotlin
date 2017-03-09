package com.ggaier.gamedev.smileyface

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.ExtendViewport

/**
 * Created by ggaier at 09/03/2017 .
 * jwenbo52@gmail.com
 */
const val FACE_CENTER_X = 20.0f
const val FACE_CENTER_Y = 20.0f
const val WORLD_WIDTH = 10.0f
const val WORLD_HEIGHT = 10.0f
const val FACE_RADIUS = 0.8f * WORLD_WIDTH / 2
const val EYE_OFFSET = 0.5f * FACE_RADIUS
const val EYE_RADIUS = 0.2f * FACE_RADIUS
const val MOUTH_OUTER_RADIUS = 0.8f * FACE_RADIUS
const val MOUTH_INNER_RADIUS = 0.6f * FACE_RADIUS
const val MOUTH_START_ANGLE = 180f
const val MOUTH_DEGREE = 180f
const val FACE_SEGMENTS = 40
const val MOUTH_SEGMENTS = 20
const val EYE_SEGMENTS = 20

class SmileyFace : ApplicationAdapter() {

    lateinit var mRender: ShapeRenderer
    lateinit var mViewPort: ExtendViewport

    override fun create() {
        mRender = ShapeRenderer()
        mViewPort = ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT)
    }

    override fun dispose() {
        mRender.dispose()
    }

    override fun resize(width: Int, height: Int) {
        mViewPort.update(width, height, true)

        mViewPort.camera.position.set(FACE_CENTER_X, FACE_CENTER_X, 0f)
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mViewPort.apply()

        mRender.projectionMatrix = mViewPort.camera.combined

        mRender.begin(ShapeRenderer.ShapeType.Filled)
        drawSmileyFace(mRender)
        mRender.end()
    }

    private fun drawSmileyFace(render: ShapeRenderer) {
        render.color = Color.YELLOW
        render.circle(FACE_CENTER_X, FACE_CENTER_Y, FACE_RADIUS, FACE_SEGMENTS)

        render.color = Color.BLACK
        render.circle(FACE_CENTER_X - EYE_OFFSET, FACE_CENTER_Y + EYE_OFFSET,
                EYE_OFFSET, EYE_SEGMENTS)

        render.circle(FACE_CENTER_X + EYE_OFFSET, FACE_CENTER_Y + EYE_OFFSET,
                EYE_RADIUS, EYE_SEGMENTS)

        render.arc(FACE_CENTER_X, FACE_CENTER_Y, MOUTH_OUTER_RADIUS,
                MOUTH_START_ANGLE, MOUTH_DEGREE, MOUTH_SEGMENTS)

        render.color=Color.YELLOW
        render.arc(FACE_CENTER_X, FACE_CENTER_Y, MOUTH_INNER_RADIUS,
                MOUTH_START_ANGLE, MOUTH_DEGREE, MOUTH_SEGMENTS)

    }


}