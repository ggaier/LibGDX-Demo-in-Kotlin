package com.ggaier.gamedev.dragoncurve

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

/**
 * Created by ggaier at 03/03/2017 .
 * jwenbo52@Gmail.com
 */
class DragonCurve() :ApplicationAdapter(){

    lateinit var mDragonCurve:FloatArray

    lateinit var mShapeRenderer:ShapeRenderer

    private val recursions=10

    override fun create() {
        mDragonCurve =DragonCurveGenerator.generateDragonCurve(Gdx.graphics.width,
                Gdx.graphics.height,recursions)
        mShapeRenderer=ShapeRenderer()
    }

    override fun render() {
        Gdx.gl.glClearColor(0f,0f,0f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mShapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        mShapeRenderer.polyline(mDragonCurve)
        mShapeRenderer.end()
    }



}