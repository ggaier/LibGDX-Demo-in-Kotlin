package main.java.com.ggaier

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import java.util.*

/**
 * Created by ggaier at 27/02/2017 .
 * jwenbo52@Gmail.com
 */
class StartFiled : ApplicationAdapter(){

    val STAR_DENSITY: Float =0.1f
    lateinit var mShapeRender: ShapeRenderer
    lateinit var mStars: Array<Vector2>

    override fun create() {
        super.create()
        initStars(STAR_DENSITY)
        mShapeRender= ShapeRenderer()
//        mShapeRender.color=Color.BLUE
    }

    override fun resize(width: Int, height: Int) {
        initStars(STAR_DENSITY)
        mShapeRender= ShapeRenderer()
//        mShapeRender.color=Color.BLUE
    }

    override fun render() {
        Gdx.gl.glClearColor(0f,0f,0f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mShapeRender.begin(ShapeRenderer.ShapeType.Point)
        for(star in mStars){
            mShapeRender.point(star.x,star.y,0f)
        }
        mShapeRender.end()

    }


    override fun dispose() {
        mShapeRender.dispose()
        super.dispose()
    }

    fun initStars(density : Float){
        val screenWidth=Gdx.graphics.width
        val screenHeight=Gdx.graphics.height
        val starCount: Int=(screenHeight*screenWidth*density).toInt()
        mStars= Array(starCount)
        val random=Random()
        for (i in 1..starCount){
            val x:Float=random.nextInt(screenWidth).toFloat()
            val y: Float=random.nextInt(screenHeight).toFloat()
            mStars.add(Vector2(x,y))
        }
    }

}