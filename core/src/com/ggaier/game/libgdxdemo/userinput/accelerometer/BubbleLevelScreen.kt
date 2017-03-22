package com.ggaier.game.libgdxdemo.userinput.accelerometer

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.ScreenViewport

/**
 * Created by ggaier at 22/03/2017 .
 * jwenbo52@gmail.com
 */
private const val WORLD_SIZE = 100.0f
private const val TEXT_SCALE = 5.0f
private const val AXIS_WIDTH = 1.0f

class BubbleLevelScreen : ScreenAdapter() {

    val AXIS_COLOR = Color.RED

    lateinit var mRenderer: ShapeRenderer
    lateinit var mAxisViewport: FitViewport

    lateinit var mBatch: SpriteBatch
    lateinit var mFont: BitmapFont
    lateinit var mTextViewport: ScreenViewport

    var mMaxAcceleration = Float.MAX_VALUE
    var mMinAcceleration = Float.MIN_VALUE

    override fun show() {
        mRenderer = ShapeRenderer()
        mRenderer.setAutoShapeType(true)
        mAxisViewport = FitViewport(WORLD_SIZE, WORLD_SIZE)
        mBatch = SpriteBatch()
        mTextViewport = ScreenViewport()
        mFont = BitmapFont()
        mFont.data.setScale(TEXT_SCALE)
        mFont.region.texture.setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear)
        mMaxAcceleration = 0f
        mMinAcceleration = Float.MAX_VALUE
    }


    override fun resize(width: Int, height: Int) {
        mAxisViewport.update(width, height, true)
        mTextViewport.update(width, height, true)
    }

    override fun dispose() {
        mRenderer.dispose()
        mFont.dispose()
        mBatch.dispose()
    }

    override fun render(delta: Float) {
        mTextViewport.apply()
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        val xAxis = Gdx.input.accelerometerX
        val yAxis = Gdx.input.accelerometerY
        val zAxis = Gdx.input.accelerometerZ

        var totalAcceleration = Math.sqrt(
                (xAxis * xAxis + yAxis * yAxis + zAxis * zAxis).toDouble()).toFloat()
        totalAcceleration += 0.00001f

        mMaxAcceleration = Math.max(mMaxAcceleration, totalAcceleration)
        mMinAcceleration = Math.min(mMinAcceleration, totalAcceleration)

        mBatch.projectionMatrix = mTextViewport.camera.combined
        mBatch.begin()
        val message = "Accelerometer reads: " +
                "\nx=$xAxis\ny=$yAxis\nz=$zAxis\ntotal=$totalAcceleration\nmax=${mMaxAcceleration
                        .format(2)}" +
                "\nmin=${mMinAcceleration.format(2)} "
        mFont.draw(mBatch, message, 40f, mTextViewport.worldHeight - 40f)
        mBatch.end()

        mAxisViewport.apply()
        mRenderer.projectionMatrix = mAxisViewport.camera.combined
        mRenderer.begin(ShapeRenderer.ShapeType.Line)
        mRenderer.color = Color.RED
        mRenderer.circle(WORLD_SIZE / 2, WORLD_SIZE / 2, WORLD_SIZE / 40, 64)

        mRenderer.end()
    }

    /**
     * this is called extension functions. the "this " in extension funtion
     * denotes the receiver parameter that is passed on the left-hand side
     * of a dot, which in this method is a float receiver
     */
    private fun Float.format(digits: Int) = String.format("%.${digits}f", this)

}