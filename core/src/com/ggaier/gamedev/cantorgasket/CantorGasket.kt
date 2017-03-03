package com.ggaier.gamedev.cantorgasket

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle

/**
 * Created by ggaier at 03/03/2017 .
 * jwenbo52@Gmail.com
 */
class CantorGasket() : ApplicationAdapter() {

    lateinit var mShapeRenderer: ShapeRenderer
    val RECURSIONS = 5

    override fun create() {
        mShapeRenderer = ShapeRenderer()
    }


    override fun render() {
        //set the canvas background to black,and clear GPU buffer
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        val bounds: Rectangle = findLargestSquare()

        mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        //绘制白底方块
        mShapeRenderer.color= Color.WHITE
        mShapeRenderer.rect(bounds.x,bounds.y,bounds.width,bounds.height)

        //打出黑色的方块点数
        mShapeRenderer.color= Color.BLACK
        punchCantorGasket(bounds.x,bounds.y,bounds.width,RECURSIONS)
        mShapeRenderer.end()
    }

    private fun punchCantorGasket(x: Float, y: Float, size: Float, recursions: Int=5) {
        var vRecursions=recursions
        if(vRecursions==0)return
        val newSize=size/3f
        val newSize2=newSize*2

        //绘制出正中间的第一个黑色方块
        mShapeRenderer.rect(x+newSize,y+newSize,newSize,newSize)

        vRecursions--

        punchCantorGasket(x,y,newSize,vRecursions)
        punchCantorGasket(x,y+newSize,newSize,vRecursions)
        punchCantorGasket(x,y+newSize2,newSize,vRecursions)

        punchCantorGasket(x+newSize,y,newSize,vRecursions)
        punchCantorGasket(x+newSize,y+newSize2,newSize,vRecursions)

        punchCantorGasket(x+newSize2,y,newSize,vRecursions)
        punchCantorGasket(x+newSize2,y+newSize,newSize,vRecursions)
        punchCantorGasket(x+newSize2,y+newSize2,newSize,vRecursions)

//        for(i in 0..8){
//            if(i==4)continue
//            punchCantorGasket(i%3*newSize+x,i/3*newSize+y,newSize,vRecursions)
//        }
    }


    private fun findLargestSquare(): Rectangle {
        val largestSquare=Rectangle()
        val screenWidth=Gdx.graphics.width
        val screenHeight=Gdx.graphics.height
        if (screenWidth > screenHeight) {//确定正方形的位置
            largestSquare.x=((screenWidth-screenHeight)/2).toFloat()
            largestSquare.y=0f
            largestSquare.width=screenHeight.toFloat()
            largestSquare.height=screenHeight.toFloat()
        }else{
            largestSquare.x=0f
            largestSquare.y=((screenHeight-screenWidth)/2).toFloat()
            largestSquare.width=screenWidth.toFloat()
            largestSquare.height=screenWidth.toFloat()
        }
        return largestSquare
    }

    override fun dispose() {
        super.dispose()
        mShapeRenderer.dispose()
    }

}