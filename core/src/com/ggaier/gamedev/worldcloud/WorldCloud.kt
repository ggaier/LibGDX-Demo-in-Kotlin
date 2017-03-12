package com.ggaier.gamedev.worldcloud

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils


/**
 * Created by ggaier at 10/03/2017 .
 * jwenbo52@gmail.com
 */
private const val WORD_COUNT = 20
private const val MIN_SCALE = 0.5f
private const val MAX_SCALE = 5.0f

class WorldCloud : ApplicationAdapter() {

    /**
     * 批量画四方格
     */
    lateinit var mBatch: SpriteBatch

    lateinit var mFont: BitmapFont

    private lateinit var mWords: Array<Word>

    override fun create() {
        mBatch = SpriteBatch()
        mFont = BitmapFont()
        mFont.region.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        mWords = generateWords(WORD_COUNT)

    }

    override fun dispose() {
        mFont.dispose()
        mBatch.dispose()
    }

    override fun resize(width: Int, height: Int) {
        mBatch = SpriteBatch()
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mBatch.begin()

        for (word in mWords) {
            // 设置字体的缩放
            mFont.data.setScale(word.scale)
            // 设置字体的颜色
            mFont.color = word.color

            //真正的绘制字符
            mFont.draw(mBatch, word.letters, word.x * Gdx.graphics.width,
                    word.y * Gdx.graphics.height)

        }
        mBatch.end()

    }

    private fun generateWords(wordCount: Int): Array<Word> {
        val words = Array(wordCount, {
            Word.randomWord(MIN_SCALE, MAX_SCALE)
        })
        return words
    }

    internal class Word(var x: Float, var y: Float, var scale: Float, var color: Color,
                        var letters: String) {
        companion object {

            private val WORDS = arrayOf("render-farm", "refrigerator", "tiger-team", "weathered", "camera", "tattoo", "boat", "soul-delay", "nodal point", "motion augmented", "reality neon", "nano-construct", "garage", "bicycle", "rebar tanto", "modem", "concrete RAF", "industrial grade media", "realism", "drone", "post-franchise shoes", "render-farm-ware", "DIY San Francisco", "rain lights", "numinous tank-traps", "pen drone", "cyber-cardboard", "denim monofilament", "order-flow", "smart-hotdog")

            fun randomWord(minScale: Float, maxScale: Float): Word {
                val x = MathUtils.random(-.25f, .75f)
                val y = MathUtils.random()
                val scale = minScale + (maxScale - minScale) * MathUtils.random()
                val color = Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1f)
                val letters = WORDS[MathUtils.random(WORDS.size - 1)]
                return Word(x, y, scale, color, letters)
            }
        }
    }

}

