package com.ggaier.gamedev.movement.applicationadaptertogame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport

/**
 * Created by ggaier at 16/03/2017 .
 * jwenbo52@gmail.com
 */
class MyScreen : Screen {

    lateinit var mBatch: SpriteBatch
    lateinit var mFont: BitmapFont
    lateinit var mViewport: ScreenViewport

    override fun hide() {
        mBatch.dispose()
        mFont.dispose()
    }

    override fun show() {
        mBatch = SpriteBatch()
        mFont = BitmapFont()
        mFont.data.setScale(2f)
        mFont.region.texture.setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear)
        mViewport = ScreenViewport()
    }

    override fun render(delta: Float) {
        mViewport.apply()
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mBatch.projectionMatrix = mViewport.camera.combined
        mBatch.begin()
        mFont.draw(mBatch, "Hello from MyScreen ", mViewport.worldWidth / 2,
                mViewport.worldHeight / 2,
                0f, Align.center, false)

        mBatch.end()
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun resize(width: Int, height: Int) {
        mViewport.update(width, height, true)
    }

    override fun dispose() {
    }

}