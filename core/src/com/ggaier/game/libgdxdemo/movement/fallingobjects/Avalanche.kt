package com.ggaier.game.libgdxdemo.movement.fallingobjects

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.Viewport
import java.util.*

/**
 * Created by ggaier at 18/03/2017 .
 * jwenbo52@gmail.com
 */
private const val SPAWNS_PER_SECOND = 10

class Avalanche {

    val mBoulder = mutableListOf<Boulder>()

    fun update(delta: Float, viewport: Viewport) {
        val random = Random()
        if (random.nextFloat() < delta * SPAWNS_PER_SECOND) {
            mBoulder.add(Boulder(viewport))
        }

        for ((index, boulder) in mBoulder.withIndex()) {
            boulder.update(delta)
            if (boulder.isBelowScreen()) {
                mBoulder.removeAt(index)
            }
        }
    }

    fun render(renderer: ShapeRenderer) {
        for (boulder in mBoulder) {
            boulder.render(renderer)
        }
    }


}