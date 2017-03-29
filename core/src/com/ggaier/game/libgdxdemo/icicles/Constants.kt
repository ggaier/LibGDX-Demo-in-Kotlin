package com.ggaier.game.libgdxdemo.icicles

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2

/**
 * Created by ggaier at 25/03/2017 .
 * jwenbo52@gmail.com
 */
val ICICLE_COLOR: Color = Color.WHITE
val BACKGROUND_COLOR: Color = Color.BLUE
const val ICICLE_WIDTH = 0.5f
const val ICICLE_HEIGHT = 1.0f
const val WORLD_SIZE = 10.0f
val PLAYER_COLOR: Color = Color.BLACK
const val PLAYER_HEADER_RADIUS = 0.5f
const val PLAYER_HEAD_HEIGHT = 4.0f * PLAYER_HEADER_RADIUS
const val PLAYER_HEAD_SEGMENTS = 20
const val PLAYER_LIMB_WIDTH = 0.1f
const val PLAYER_MOVEMENT_SPEED = 10.0f
const val GRAVITY_ACCELERATION = 9.8f
const val ACCELEROMETER_SENSITIVITY = 0.5f
const val ICICLE_SPAWNS_PER_SECOND = 10.0f
val ICICLES_ACCELERATION: Vector2 = Vector2(0f, -5f)
const val HUD_FONT_REFERENCE_SCREEN_SIZE = 480.0f
const val HUD_MARGIN = 20.0f

const val EASY_SPAWNS_PER_SECOND: Float = 5f
const val MEDIUM_SPAWNS_PER_SECOND: Float = 15f
const val HARD_SPAWNS_PER_SECOND: Float = 20f
const val EASY_LABEL = "Cold"
const val MEDIUM_LABLE = "Colder"
const val HARD_LABLE = "Coldest"

enum class Difficulty(val spawnRate: Float, val label: String) {

    EASY(EASY_SPAWNS_PER_SECOND, EASY_LABEL),
    MEDIUM(MEDIUM_SPAWNS_PER_SECOND, MEDIUM_LABLE),
    HARD(HARD_SPAWNS_PER_SECOND, HARD_LABLE)

}