package com.ggaier.game.libgdxdemo.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.ScreenViewport

/**
 * Created by ggaier at 25/03/2017 .
 * jwenbo52@gmail.com
 */
class IcicleScreen : ScreenAdapter() {

    lateinit var mIciclesViewport: ExtendViewport
    lateinit var mRenderer: ShapeRenderer
    lateinit var mIcicles: Icicles
    lateinit var mPlayer: Player

    /**
     * 游戏的大小基于屏幕尺寸的大小
     */
    lateinit var mHudViewport: ScreenViewport
    /**
     * Sprite  指的是计算机图形学。
     */
    lateinit var mBatch: SpriteBatch
    lateinit var mFont: BitmapFont
    var mTopScore: Int = 0

    override fun show() {
        mIciclesViewport = ExtendViewport(WORLD_SIZE, WORLD_SIZE)
        mRenderer = ShapeRenderer()
        mRenderer.setAutoShapeType(true)
        mIcicles = Icicles(mIciclesViewport)
        mPlayer = Player(mIciclesViewport)

        mHudViewport = ScreenViewport()
        mBatch = SpriteBatch()
        mFont = BitmapFont()
        //用来设置缩小和放大的过滤
        mFont.region.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
    }

    override fun render(delta: Float) {
        mIcicles.update(delta)
        mPlayer.update(delta)
        if (mPlayer.hitByIcicle(mIcicles)) {
            mIcicles.init()
        }
        mIciclesViewport.apply(true)
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g, BACKGROUND_COLOR.b, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mRenderer.projectionMatrix = mIciclesViewport.camera.combined
        mRenderer.begin(ShapeRenderer.ShapeType.Filled)
        mRenderer.color = ICICLE_COLOR
        mIcicles.render(mRenderer)
        mPlayer.render(mRenderer)
        mRenderer.end()

        //绘制分数。
        mTopScore = Math.max(mTopScore, mIcicles.mIciclesDodged)
        mHudViewport.apply()
        mBatch.projectionMatrix = mHudViewport.camera.combined
        mBatch.begin()
        mFont.draw(mBatch, "Deaths: ${mPlayer.mDeaths} ", HUD_MARGIN,
                mHudViewport.worldHeight - HUD_MARGIN)
        mFont.draw(mBatch, "Score: ${mIcicles.mIciclesDodged} \n Top Score: $mTopScore",
                mHudViewport.worldWidth - HUD_MARGIN, mHudViewport.worldHeight - HUD_MARGIN, 0f,
                Align.right, false)
        mBatch.end()
    }

    override fun resize(width: Int, height: Int) {
        mIciclesViewport.update(width, height)
        mHudViewport.update(width, height, true)
        mFont.data.setScale(Math.min(width, height) / HUD_FONT_REFERENCE_SCREEN_SIZE)
        mPlayer.init()
        mIcicles.init()
    }

    override fun dispose() {
        mRenderer.dispose()
        mBatch.dispose()
        mFont.dispose()
    }

    override fun hide() {
        mRenderer.dispose()
    }

}