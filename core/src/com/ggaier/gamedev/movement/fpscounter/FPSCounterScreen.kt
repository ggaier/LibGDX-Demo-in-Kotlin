package com.ggaier.gamedev.movement.fpscounter

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.utils.viewport.Viewport

/**
 * Created by ggaier at 16/03/2017 .
 * jwenbo52@gmail.com
 */
class FPSCounterScreen : ScreenAdapter() {

    lateinit var mBatch: SpriteBatch
    lateinit var mFont: BitmapFont
    lateinit var mViewport: Viewport

    override fun show() {
        mBatch= SpriteBatch()
        mFont= BitmapFont()
        mFont.data.setScale(2f)
        mFont.region.texture.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear)
        mViewport=ScreenViewport()
    }

    override fun resize(width: Int, height: Int) {
        mViewport.update(width,height,true)
    }

    /**
     * @param delta 表示自最后一次渲染到本次渲染的时间
     */
    override fun render(delta: Float) {
        mViewport.apply()
        Gdx.gl.glClearColor(0f,0f,0f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mBatch.projectionMatrix=mViewport.camera.combined
        mBatch.begin()

        val fps= 1/delta

        mFont.draw(mBatch,"FPS = $fps",mViewport.worldWidth/4,mViewport.worldHeight/2)
        mBatch.end()
    }

    override fun dispose() {
        mBatch.dispose()
        mFont.dispose()

    }

}