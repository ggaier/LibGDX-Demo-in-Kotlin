package com.ggaier.game.libgdxdemo.flower

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

/**
 * Created by ggaier at 02/03/2017 .
 * jwenbo52@Gmail.com
 */
class RectangleFlower():ApplicationAdapter(){

    lateinit var mShapeRenderer:ShapeRenderer

    override fun create() {
        mShapeRenderer = ShapeRenderer()
    }

    override fun render() {
        Gdx.gl.glClearColor(0f,0f,0f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        mShapeRenderer.color=Color.GREEN
        mShapeRenderer.rectLine(100f,0f,100f,300f,20f)
        mShapeRenderer.rect(100f,100f,0f,0f,40f,40f,1f,1f,135f)
        mShapeRenderer.rect(100f,150f,0f,0f,30f,30f,1f,1f,315f)


        mShapeRenderer.color=Color.YELLOW
        for (i in 1..20) {
            mShapeRenderer.rect(100f,300f,0f,0f,40f,40f,1f,1f,(360*i/20).toFloat())
        }

        mShapeRenderer.end()
    }

    override fun dispose() {
        super.dispose()
        mShapeRenderer.dispose()
    }




}