package com.ggaier.game.libgdxdemo.viewports

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport

/**
 * Created by ggaier at 07/03/2017 .
 * jwenbo52@gmail.com
 */
const val WORLD_WIDTH=100.0f
const val WORLD_HEIGHT=100.0f
const val RECURSIONS=3
//todo: update this drawing
class ViewportsExercise :ApplicationAdapter(){

    lateinit var mShapeRender:ShapeRenderer
    lateinit var mViewPort:FitViewport

    override fun create() {
        mShapeRender= ShapeRenderer()
        mViewPort= FitViewport(WORLD_WIDTH, WORLD_HEIGHT)
    }

    override fun dispose() {
        mShapeRender.dispose()
    }

    override fun resize(width: Int, height: Int) {
        mViewPort.update(width,height,true)
    }

    override fun render() {
        Gdx.gl.glClearColor(0f,0f,0f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mViewPort.apply()
        mShapeRender.projectionMatrix=mViewPort.camera.combined

        mShapeRender.begin(ShapeRenderer.ShapeType.Filled)
        mShapeRender.color=Color.WHITE
        mShapeRender.rect(0f,0f, WORLD_WIDTH, WORLD_HEIGHT)

        mShapeRender.color=Color.BLACK
        punchCantorGasket(0f,0f, WORLD_WIDTH, WORLD_HEIGHT, RECURSIONS)

        mShapeRender.color=Color.WHITE
        mShapeRender.circle(WORLD_WIDTH, WORLD_HEIGHT,Math.min(WORLD_HEIGHT, WORLD_WIDTH))
        mShapeRender.end()


    }

    private fun punchCantorGasket(x: Float, y: Float, width: Float, height: Float, recursions: Int) {
        if (recursions == 0) {
            return
        }
        val newWidth = width / 3
        val newHeight = height / 3
        mShapeRender.rect(x + newWidth, y + newHeight, newWidth, newHeight)

        punchCantorGasket(x, y, newWidth, newHeight, recursions - 1)
        punchCantorGasket(x + newWidth, y, newWidth, newHeight, recursions - 1)
        punchCantorGasket(x + 2 * newWidth, y, newWidth, newHeight, recursions - 1)
        punchCantorGasket(x, y + newHeight, newWidth, newHeight, recursions - 1)
        punchCantorGasket(x + 2 * newWidth, y + newHeight, newWidth, newHeight, recursions - 1)
        punchCantorGasket(x, y + 2 * newHeight, newWidth, newHeight, recursions - 1)
        punchCantorGasket(x + newWidth, y + 2 * newHeight, newWidth, newHeight, recursions - 1)
        punchCantorGasket(x + 2 * newWidth, y + 2 * newHeight, newWidth, newHeight, recursions - 1)
    }

}