package com.ggaier.game.libgdxdemo.icicles

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport

/**
 * Created by ggaier at 25/03/2017 .
 * jwenbo52@gmail.com
 */
class DifficultyScreen(val mIciclesGame: IciclesGame) : Screen, InputAdapter() {

    private val TAG: String = "Screen"

    lateinit var mRenderer: ShapeRenderer
    lateinit var mBatch: SpriteBatch
    lateinit var mViewport: FitViewport
    lateinit var mFont: BitmapFont

    override fun show() {
        mRenderer = ShapeRenderer()
        mBatch = SpriteBatch()
        mViewport = FitViewport(DIFFICULTY_WORLD_SIZE, DIFFICULTY_WORLD_SIZE)
        Gdx.input.inputProcessor = this

        mFont = BitmapFont()
        mFont.data.setScale(DIFFICULTY_LABEL_SCALE)
        mFont.region.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
    }

    override fun render(delta: Float) {
        mViewport.apply()
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g, BACKGROUND_COLOR.b, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mRenderer.projectionMatrix = mViewport.camera.combined
        mRenderer.begin(ShapeRenderer.ShapeType.Filled)
        mRenderer.color = EASY_COLOR
        mRenderer.circle(EASY_CENTER.x, EASY_CENTER.y, DIFFICULTY_BUBBLE_RADIUS)

        mRenderer.color = MEDIUM_COLOR
        mRenderer.circle(MEDIUM_CENTER.x, MEDIUM_CENTER.y, DIFFICULTY_BUBBLE_RADIUS)

        mRenderer.color = HARD_COLOR
        mRenderer.circle(HARD_CENTER.x, HARD_CENTER.y, DIFFICULTY_BUBBLE_RADIUS)
        mRenderer.end()

        mBatch.projectionMatrix = mViewport.camera.combined
        mBatch.begin()
        val easyLayout = GlyphLayout(mFont, EASY_LABEL)
        mFont.draw(mBatch, EASY_LABEL, EASY_CENTER.x, EASY_CENTER.y + easyLayout.height / 2, 0f,
                Align.center, false)

        val mediumLayout = GlyphLayout(mFont, MEDIUM_LABEL)
        mFont.draw(mBatch, MEDIUM_LABEL, MEDIUM_CENTER.x, MEDIUM_CENTER.y + mediumLayout.height / 2,
                0f, Align.center, false)

        val hardLayout = GlyphLayout(mFont, HARD_LABEL)
        mFont.draw(mBatch, HARD_LABEL, HARD_CENTER.x, HARD_CENTER.y + hardLayout.height / 2, 0f,
                Align.center, false)
        mBatch.end()

    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
        mViewport.update(width, height, true)
    }

    override fun dispose() {
    }

    override fun hide() {
        mBatch.dispose()
        mFont.dispose()
        mRenderer.dispose()
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val worldTouch = mViewport.unproject(Vector2(screenX.toFloat(), screenY.toFloat()))

        if (worldTouch.dst(EASY_CENTER) < DIFFICULTY_BUBBLE_RADIUS) {
            mIciclesGame.showIciclesScreen(Difficulty.EASY)
        }

        if (worldTouch.dst(MEDIUM_CENTER) < DIFFICULTY_BUBBLE_RADIUS) {
            mIciclesGame.showIciclesScreen(Difficulty.MEDIUM)
        }

        if (worldTouch.dst(HARD_CENTER) < DIFFICULTY_BUBBLE_RADIUS) {
            mIciclesGame.showIciclesScreen(Difficulty.HARD)
        }

        return true
    }

}