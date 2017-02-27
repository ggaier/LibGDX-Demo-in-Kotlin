package main.java.com.ggaier

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

/**
 * Created by ggaier at 27/02/2017 .
 * jwenbo52@Gmail.com
 */
class StartFiled(): ApplicationAdapter(){

    val STAR_DENSITY: Float =0.01f
    lateinit var mShapeRender: ShapeRenderer

    override fun create() {
        super.create()
    }

    override fun resize(width: Int, height: Int) {
        initStars(STAR_DENSITY)
        mShapeRender= ShapeRenderer()
    }

    override fun render() {
        Gdx.gl.glClearColor(0f,0f,0f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        
    }


    override fun dispose() {
        super.dispose()
    }

    fun initStars(density : Float){

    }

}