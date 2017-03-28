package com.ggaier.game.libgdxdemo.icicles

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport

/**
 * Created by ggaier at 25/03/2017 .
 * jwenbo52@gmail.com
 */
class Icicles(val mViewport: Viewport) {

    lateinit var mIcicleList: MutableList<Icicle>

    init {
        init()
    }

    fun init() {
        mIcicleList = mutableListOf()
    }

    fun update(delta: Float) {
        if (MathUtils.random() < delta * ICICLE_SPAWNS_PER_SECOND) {
            val newIciclePosition = Vector2(MathUtils.random() * mViewport.worldWidth,
                    mViewport.worldHeight)
            val icicle = Icicle(newIciclePosition)
            mIcicleList.add(icicle)
        }
        for (icicle in mIcicleList) {
            icicle.update(delta)
        }
    }

    fun render(renderer: ShapeRenderer) {
        renderer.color = ICICLE_COLOR
        for (icicle in mIcicleList) {
            icicle.render(renderer)
        }
    }

}