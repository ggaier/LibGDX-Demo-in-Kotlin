package com.ggaier.libgdxkotlin

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
    }

    private fun startActivityWithFlag(extraFlag: Int) {
        val intent = Intent(this@HomeActivity, AndroidLauncher::class.java)
        intent.putExtra(FLAG, extraFlag)
        startActivity(intent)
    }

}