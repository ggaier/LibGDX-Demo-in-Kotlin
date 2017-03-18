package com.ggaier.gamedev.movement.fallingobjects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.ExtendViewport

/**
 * Created by ggaier at 18/03/2017 .
 * jwenbo52@gmail.com
 */
private const val WORLD_SIZE = 480f

class FallingObjectsScreen : ScreenAdapter() {

    lateinit var mRenderer: ShapeRenderer
    lateinit var mViewPort: ExtendViewport
    lateinit var mAvalanche: Avalanche

    override fun show() {
        mRenderer = ShapeRenderer()
        mViewPort = ExtendViewport(WORLD_SIZE, WORLD_SIZE)
        mAvalanche= Avalanche()
    }

    override fun resize(width: Int, height: Int) {
        mViewPort.update(width,height,true)
    }

    override fun dispose() {
        mRenderer.dispose()
    }

    override fun render(delta: Float) {
        mViewPort.apply()
        Gdx.gl.glClearColor(0f,0f,0f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mAvalanche.update(delta,mViewPort)

        mRenderer.projectionMatrix=mViewPort.camera.combined
        mRenderer.begin(ShapeRenderer.ShapeType.Filled)
        mAvalanche.render(mRenderer)
        mRenderer.end()
    }

}