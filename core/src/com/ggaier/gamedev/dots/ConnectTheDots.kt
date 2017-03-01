package com.ggaier.gamedev.dots

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array

/**
 * Created by ggaier at 01/03/2017 .
 * jwenbo52@Gmail.com
 */
class ConnectTheDots() : ApplicationAdapter() {

    val DOT_RADIUS: Float = 3.0f
    lateinit var mSpriteBatch: SpriteBatch
    lateinit var mBitmapFont: BitmapFont
    val mDots = dots()
    lateinit var mFloatDots: FloatArray
    lateinit var mShapeRender: ShapeRenderer

    override fun create() {
        mSpriteBatch = SpriteBatch()
        mBitmapFont = BitmapFont()
        mFloatDots = vector2ArrayToFloatArray(mDots)
        mShapeRender=ShapeRenderer()
    }

    fun vector2ArrayToFloatArray(dots: Array<Vector2>): FloatArray {
        val floatDots: FloatArray = FloatArray(dots.size * 2)
        var i=0
        for (dot in dots) {
            floatDots[i++] = dot.x
            floatDots[i++] = dot.y
        }
        return floatDots
    }

    override fun render() {
        Gdx.gl.glClearColor(0f,0f,0f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        //绘制点点
        mShapeRender.begin(ShapeRenderer.ShapeType.Filled)
        for (dot: Vector2 in mDots){
            mShapeRender.circle(dot.x,dot.y,DOT_RADIUS)
        }
        mShapeRender.end()

        //绘制数字
        mSpriteBatch.begin()
        var i=1
        for (dot: Vector2 in mDots){
            mBitmapFont.draw(mSpriteBatch,i.toString(),dot.x+DOT_RADIUS,
                    dot.y-DOT_RADIUS)
            i++
        }
        mSpriteBatch.end()

        //绘制连线
        mShapeRender.begin(ShapeRenderer.ShapeType.Line)
        if(mFloatDots.size>3){
            mShapeRender.polyline(mFloatDots)
        }
        mShapeRender.end()

    }



}