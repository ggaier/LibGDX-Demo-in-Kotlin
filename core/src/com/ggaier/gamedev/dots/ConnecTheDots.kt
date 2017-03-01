package com.ggaier.gamedev.dots

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array

/**
 * Created by ggaier at 01/03/2017 .
 * jwenbo52@Gmail.com
 */
class ConnecTheDots : ApplicationAdapter() {

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
}