package com.ggaier.game.libgdxdemo.icicles

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.DelayedRemovalArray
import com.badlogic.gdx.utils.viewport.Viewport

/**
 * Created by ggaier at 25/03/2017 .
 * jwenbo52@gmail.com
 */
class Icicles(val mViewport: Viewport) {

    lateinit var mIcicleList: DelayedRemovalArray<Icicle>
        private set

    var mIciclesDodged: Int = 0
        private set

    init {
        init()
    }

    fun init() {
        mIcicleList = DelayedRemovalArray(false, 100)
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

        mIcicleList.begin()
        for ((index, icicle) in mIcicleList.withIndex()) {
            if (icicle.mPosition.y < -ICICLE_HEIGHT) {
                mIcicleList.removeIndex(index)
                mIciclesDodged++
            }
        }
        mIcicleList.end()
    }

    fun render(renderer: ShapeRenderer) {
        renderer.color = ICICLE_COLOR
        for (icicle in mIcicleList) {
            icicle.render(renderer)
        }
    }

}