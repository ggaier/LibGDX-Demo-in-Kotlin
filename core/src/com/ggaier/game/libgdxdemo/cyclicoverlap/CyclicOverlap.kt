package com.ggaier.game.libgdxdemo.cyclicoverlap

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport

/**
 * Created by ggaier at 13/03/2017 .
 * jwenbo52@gmail.com
 */
private const val WORLD_SIZE = 10f

class CyclicOverlap : ApplicationAdapter() {

    lateinit var mShapeRenderer: ShapeRenderer
    lateinit var mViewPort: FitViewport

    override fun create() {
        mShapeRenderer = ShapeRenderer()
        mViewPort = FitViewport(WORLD_SIZE, WORLD_SIZE)
    }


    override fun resize(width: Int, height: Int) {
        mViewPort.update(width, height, true)
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mViewPort.apply()
        mShapeRenderer.projectionMatrix = mViewPort.camera.combined

        mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        mShapeRenderer.color = Color.RED
        mShapeRenderer.rect(2f, 3.5f, 3f, 1.5f, 6f, 1f, 1f, 1f, 0f)

        //在上一个矩形的基础上旋转120度
        mShapeRenderer.color = Color.GREEN
        mShapeRenderer.rect(2f, 3.5f, 3f, 1.5f, 6f, 1f, 1f, 1f, 120f)

        mShapeRenderer.color = Color.BLUE
        mShapeRenderer.rect(2f, 3.5f, 3f, 1.5f, 6f, 1f, 1f, 1f, 240f)

        //以上三条矩形沿着某一点分别旋转了0度，120度，240度，构成了一个循环三角形

        //最后再重新绘制部分第一个矩形，构成cyclic overlap
        mShapeRenderer.color = Color.RED
        mShapeRenderer.rect(2f, 3.5f, 3f, 1.5f, 3f, 1f, 1f, 1f, 0f)
        mShapeRenderer.end()
    }

    override fun dispose() {
        mShapeRenderer.dispose()
    }

}