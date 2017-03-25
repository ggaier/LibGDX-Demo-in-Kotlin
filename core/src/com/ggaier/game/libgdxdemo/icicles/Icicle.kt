package com.ggaier.game.libgdxdemo.icicles

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

/**
 * Created by ggaier at 25/03/2017 .
 * jwenbo52@gmail.com
 */
// ::class 获取到Kotlin class的引用，然后使用
//Kotlin class的java property 获取到java 的类引用。
private val TAG = Icicle::class.java.name

class Icicle(val mPosition: Vector2) {

    fun render(renderer: ShapeRenderer) {
        renderer.color = ICICLE_COLOR
        renderer.set(ShapeRenderer.ShapeType.Filled)
        renderer.triangle(mPosition.x, mPosition.y,
                mPosition.x - ICICLE_WIDTH / 2, mPosition.y + ICICLE_HEIGHT/ 2,
                mPosition.x + ICICLE_WIDTH / 2, mPosition.y + ICICLE_HEIGHT/ 2)

    }
}