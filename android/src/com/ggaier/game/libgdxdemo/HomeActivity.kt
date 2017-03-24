package com.ggaier.game.libgdxdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by ggaier at 02/03/2017 .
 * jwenbo52@Gmail.com
 */
const val FLAG: String = "flag"
const val FLAG_START_FILED: Int = 1
const val FLAG_CONNECT_DOTS: Int = 2
const val FLAG_SPIRAL: Int = 3
const val FLAG_FLOWER: Int = 4
const val FLAG_CANTOR_GASKET: Int = 5
const val FLAG_DRAGON_CURVE: Int = 6
const val FLAG_STICK_FIGURE: Int = 7
const val FLAG_ORTHOGRAPHIC_CAMERA: Int = 8
const val FLAG_VIEWPORTS_EXERCISE: Int = 9
const val FLAG_SMILEY_FACE: Int = 10
const val FLAG_WORLD_CLOUD: Int = 11
const val FLAG_CYCLIC_OVERLAP: Int = 12
const val FLAG_SIERPINSKI_TRIANGLE: Int = 13
const val FLAG_FANCY_CIRCULATION: Int = 14
const val FLAG_RECIPROCATING_MOTION: Int = 15
const val FLAG_ADAPTER_TO_GAME: Int = 16
const val FLAG_FPS_SCREEN: Int = 17
const val FLAG_FALLING_OBJECTS: Int = 18
const val FLAG_BOUNCE_BALL: Int = 19
const val FLAG_BOUNCE_BALL_WITH_INPUT: Int = 20
const val FLAG_BUBBLE_LEVEL: Int = 21
const val FLAG_BOUNCE_BALL_WITH_ACCELERATOR: Int = 22

class HomeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_field.setOnClickListener {
            startActivityWithFlag(FLAG_START_FILED)
        }

        connect_dots.setOnClickListener {
            startActivityWithFlag(FLAG_CONNECT_DOTS)
        }

        spiral.setOnClickListener {
            startActivityWithFlag(FLAG_SPIRAL)
        }

        draw_flower.setOnClickListener {
            startActivityWithFlag(FLAG_FLOWER)
        }

        punch_cantor_gasket.setOnClickListener {
            startActivityWithFlag(FLAG_CANTOR_GASKET)
        }

        dragon_curve.setOnClickListener {
            startActivityWithFlag(FLAG_DRAGON_CURVE)
        }

        stick_figure.setOnClickListener {
            startActivityWithFlag(FLAG_STICK_FIGURE)
        }

        orthographic_camera.setOnClickListener {
            startActivityWithFlag(FLAG_ORTHOGRAPHIC_CAMERA)
        }

        viewports_exercise.setOnClickListener {
            startActivityWithFlag(FLAG_VIEWPORTS_EXERCISE)
        }

        smiley_face.setOnClickListener {
            startActivityWithFlag(FLAG_SMILEY_FACE)
        }

        world_cloud.setOnClickListener {
            startActivityWithFlag(FLAG_WORLD_CLOUD)
        }

        cyclic_overlap.setOnClickListener {
            startActivityWithFlag(FLAG_CYCLIC_OVERLAP)
        }

        sierpinski_triangle.setOnClickListener {
            startActivityWithFlag(FLAG_SIERPINSKI_TRIANGLE)
        }

        fancy_circulation.setOnClickListener {
            startActivityWithFlag(FLAG_FANCY_CIRCULATION)
        }
        reciprocating_motion.setOnClickListener {
            startActivityWithFlag(FLAG_RECIPROCATING_MOTION)
        }

        adapter_to_game.setOnClickListener {
            startActivityWithFlag(FLAG_ADAPTER_TO_GAME)
        }
        fps_screen.setOnClickListener {
            startActivityWithFlag(FLAG_FPS_SCREEN)
        }
        falling_objects.setOnClickListener {
            startActivityWithFlag(FLAG_FALLING_OBJECTS)
        }
        bounce_ball.setOnClickListener {
            startActivityWithFlag(FLAG_BOUNCE_BALL)
        }
        bounce_ball_with_input.setOnClickListener {
            startActivityWithFlag(FLAG_BOUNCE_BALL_WITH_INPUT)
        }
        bubble_level.setOnClickListener {
            startActivityWithFlag(FLAG_BUBBLE_LEVEL)
        }

        bounce_ball_with_accelerator.setOnClickListener {
            startActivityWithFlag(FLAG_BOUNCE_BALL_WITH_ACCELERATOR)
        }
    }

    private fun startActivityWithFlag(extraFlag: Int) {
        val intent = Intent(this@HomeActivity, AndroidLauncher::class.java)
        intent.putExtra(FLAG, extraFlag)
        startActivity(intent)
    }

}