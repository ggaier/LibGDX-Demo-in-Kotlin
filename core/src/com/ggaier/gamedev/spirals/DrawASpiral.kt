package com.ggaier.gamedev.spirals

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

/**
 * Created by ggaier at 02/03/2017 .
 * jwenbo52@Gmail.com
 */
class DrawASpiral() : ApplicationAdapter() {

    val COILS = 20
    lateinit var mShapeRenderer: ShapeRenderer

    override fun create() {
        mShapeRenderer = ShapeRenderer()
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mShapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        val screenWidth = Gdx.graphics.width
        val screenHeight = Gdx.graphics.height
        val xStep = screenWidth / 2 / COILS
        val yStep = screenHeight / 2 / COILS
        for(i in 0..COILS-1){
            val xOffset=xStep*i.toFloat()
            val yOffset=yStep*i.toFloat()
            val point1 =Vector2(xOffset-xStep,yOffset)
            val point2=Vector2(screenWidth-xOffset,yOffset)
            val point3=Vector2(screenWidth-xOffset,screenHeight-yOffset)
            val point4=Vector2(xOffset,screenHeight-yOffset)
            val point5=Vector2(xOffset,yStep+yOffset)
            mShapeRenderer.line(point1,point2)
            mShapeRenderer.line(point2,point3)
            mShapeRenderer.line(point3,point4)
            mShapeRenderer.line(point4,point5)
        }
        mShapeRenderer.end()

    }

}