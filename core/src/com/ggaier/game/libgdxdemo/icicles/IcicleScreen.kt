package com.ggaier.game.libgdxdemo.icicles

import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.ExtendViewport

/**
 * Created by ggaier at 25/03/2017 .
 * jwenbo52@gmail.com
 */
class IcicleScreen : ScreenAdapter() {

    lateinit var mIcicleViewport: ExtendViewport
    lateinit var mRenderer: ShapeRenderer
    lateinit var mIcicle: Icicle

    override fun show() {
        mIcicleViewport= ExtendViewport(WORLD_SIZE, WORLD_SIZE)
        mRenderer= ShapeRenderer()
        mRenderer.setAutoShapeType(true)
        mIcicle=Icicle(Vector2(WORLD_SIZE/2, WORLD_SIZE/2))
    }

    override fun resize(width: Int, height: Int) {
        mIcicleViewport.update(width,height)
    }

    override fun dispose() {
        mRenderer.dispose()
    }



}