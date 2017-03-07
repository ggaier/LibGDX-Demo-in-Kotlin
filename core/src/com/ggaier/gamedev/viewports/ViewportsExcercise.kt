package com.ggaier.gamedev.viewports

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
class ViewportsExcercise:ApplicationAdapter(){

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
        

    }

}