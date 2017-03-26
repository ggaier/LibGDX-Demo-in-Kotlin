package com.ggaier.game.libgdxdemo.icicles

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport

/**
 * Created by ggaier at 25/03/2017 .
 * jwenbo52@gmail.com
 */
private val TAG: String = Player::class.java.name

class Player(val mViewport:Viewport) {

    lateinit var mPosition: Vector2

    init {
        init()
    }

    fun init() {
        mPosition= Vector2(mViewport.worldWidth/2, PLAYER_HEAD_HEIGHT)
    }

    fun render(renderer:ShapeRenderer){
        renderer.color= PLAYER_COLOR
        renderer.set(ShapeRenderer.ShapeType.Filled)
        renderer.circle(mPosition.x,mPosition.y, PLAYER_HEADER_RADIUS, PLAYER_HEAD_SEGMENTS)

        val torsoTop=Vector2(mPosition.x,mPosition.y- PLAYER_HEADER_RADIUS)
        val torsoBottom=Vector2(torsoTop.x,torsoTop.y-2* PLAYER_HEADER_RADIUS)
        renderer.rectLine(torsoTop,torsoBottom, PLAYER_LIMB_WIDTH)
        renderer.rectLine(torsoTop.x,torsoTop.y,
                torsoTop.x+ PLAYER_HEADER_RADIUS,torsoTop.y- PLAYER_HEADER_RADIUS,
                PLAYER_LIMB_WIDTH)

        renderer.rectLine(torsoTop.x,torsoTop.y,
                torsoTop.x- PLAYER_HEADER_RADIUS,torsoTop.y- PLAYER_HEADER_RADIUS,
                PLAYER_LIMB_WIDTH)
        renderer.rectLine(torsoBottom.x,torsoBottom.y,
                torsoBottom.x+ PLAYER_HEADER_RADIUS,torsoBottom.y- PLAYER_HEADER_RADIUS,
                PLAYER_LIMB_WIDTH)
        renderer.rectLine(torsoBottom.x,torsoBottom.y,
                torsoBottom.x- PLAYER_HEADER_RADIUS,torsoBottom.y- PLAYER_HEADER_RADIUS,
                PLAYER_LIMB_WIDTH)
    }



}