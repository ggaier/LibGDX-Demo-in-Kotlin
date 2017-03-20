package com.ggaier.game.libgdxdemo.sierpinskitriangle

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.FitViewport

/**
 * Created by ggaier at 14/03/2017 .
 * jwenbo52@gmail.com
 */
private const val SIZE = 10f
private const val RECURSIONS = 7

class SierpinskiTriangle : ApplicationAdapter() {

    lateinit var mViewPort: FitViewport
    lateinit var mRenderer: ShapeRenderer

    override fun create() {
        mRenderer = ShapeRenderer()
        mViewPort = FitViewport(SIZE, SIZE)
    }


    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mViewPort.apply()
        mRenderer.projectionMatrix = mViewPort.camera.combined

        mRenderer.begin(ShapeRenderer.ShapeType.Filled)
        inscribeSierpinskiTriangle(mRenderer, SIZE, RECURSIONS)
        mRenderer.end()
    }

    private fun inscribeSierpinskiTriangle(renderer: ShapeRenderer, size: Float,
                                           recursions: Int) {
        val corner1 = Vector2(0f, 0f)
        val corner2 = Vector2(size, 0f)
        val corner3 = Vector2(size / 2, size * MathUtils.sin(MathUtils.PI / 3))
        drawSierpinskiTriangle(renderer, corner1, corner2, corner3, recursions)
    }

    private fun drawSierpinskiTriangle(renderer: ShapeRenderer,
                                       corner1: Vector2,
                                       corner2: Vector2,
                                       corner3: Vector2,
                                       recursions: Int) {
        val midPoint12 = Vector2((corner1.x + corner2.x) / 2, (corner1.y + corner2.y) / 2)
        val midPoint23 = Vector2((corner2.x + corner3.x) / 2, (corner2.y + corner3.y) / 2)
        val midPoint31 = Vector2((corner3.x + corner1.x) / 2, (corner3.y + corner1.y) / 2)
        if (recursions == 1) {
            renderer.triangle(corner1.x, corner1.y, midPoint12.x, midPoint12.y,
                    midPoint31.x, midPoint31.y)
            renderer.triangle(corner2.x, corner2.y, midPoint23.x, midPoint23.y,
                    midPoint12.x, midPoint12.y)
            renderer.triangle(corner3.x, corner3.y, midPoint31.x, midPoint31.y,
                    midPoint23.x, midPoint23.y)
        } else {
            drawSierpinskiTriangle(renderer, corner1, midPoint12, midPoint31, recursions - 1)
            drawSierpinskiTriangle(renderer, corner2, midPoint23, midPoint12, recursions - 1)
            drawSierpinskiTriangle(renderer, corner3, midPoint31, midPoint23, recursions - 1)
        }
    }

    override fun dispose() {
        mRenderer.dispose()
    }

    override fun resize(width: Int, height: Int) {
        mViewPort.update(width, height, true)

    }

}