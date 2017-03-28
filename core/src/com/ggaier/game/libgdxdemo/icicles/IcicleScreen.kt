package com.ggaier.game.libgdxdemo.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.ExtendViewport

/**
 * Created by ggaier at 25/03/2017 .
 * jwenbo52@gmail.com
 */
class IcicleScreen : ScreenAdapter() {

    lateinit var mIciclesViewport: ExtendViewport
    lateinit var mRenderer: ShapeRenderer
    lateinit var mIcicles: Icicles
    lateinit var mPlayer: Player

    override fun show() {
        mIciclesViewport = ExtendViewport(WORLD_SIZE, WORLD_SIZE)
        mRenderer = ShapeRenderer()
        mRenderer.setAutoShapeType(true)
        mIcicles = Icicles(mIciclesViewport)
        mPlayer = Player(mIciclesViewport)
    }

    override fun render(delta: Float) {
        mIcicles.update(delta)
        mPlayer.update(delta)
        if(mPlayer.hitByIcicle(mIcicles)){
            mIcicles.init()
        }
        mIciclesViewport.apply(true)
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g, BACKGROUND_COLOR.b, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mRenderer.projectionMatrix = mIciclesViewport.camera.combined
        mRenderer.begin(ShapeRenderer.ShapeType.Filled)
        mRenderer.color = ICICLE_COLOR
        mIcicles.render(mRenderer)
        mPlayer.render(mRenderer)
        mRenderer.end()
    }

    override fun resize(width: Int, height: Int) {
        mIciclesViewport.update(width, height)
        mPlayer.init()
        mIcicles.init()
    }

    override fun dispose() {
        mRenderer.dispose()
    }

    override fun hide() {
        mRenderer.dispose()
    }

}