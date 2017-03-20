package com.ggaier.game.libgdxdemo.stickfigure

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

/**
 * Created by ggaier at 04/03/2017 .
 * jwenbo52@gmail.com
 */
class DrawAStickFigure():ApplicationAdapter(){

    lateinit var mShapeRender:ShapeRenderer

    override fun create() {
        mShapeRender= ShapeRenderer()

    }

    override fun render() {
        Gdx.gl.glClearColor(0f,0f,0f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        //首先在（100，100）的位置绘制一个圆
        mShapeRender.begin(ShapeRenderer.ShapeType.Filled)
        mShapeRender.circle(100f,100f,10f)
        mShapeRender.end()

        //滑出躯干
        mShapeRender.begin(ShapeRenderer.ShapeType.Line)
        mShapeRender.line(100f,50f,100f,100f)

        //画出双脚
        mShapeRender.line(85f,35f,100f,50f)
        mShapeRender.line(115f,35f,100f,50f)

        //draw the arms
        mShapeRender.line(85f,70f,100f,85f)
        mShapeRender.line(115f,70f,100f,85f)
        mShapeRender.end()


    }

    override fun dispose() {
        super.dispose()
        mShapeRender.dispose()
    }

}