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

class HomeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this@HomeActivity, AndroidLauncher::class.java)
        start_field.setOnClickListener {
            intent.putExtra(FLAG, FLAG_START_FILED)
            startActivity(intent)
        }

        connect_dots.setOnClickListener {
            intent.putExtra(FLAG, FLAG_CONNECT_DOTS)
            startActivity(intent)
        }

        spiral.setOnClickListener {
            intent.putExtra(FLAG, FLAG_SPIRAL)
            startActivity(intent)
        }

        draw_flower.setOnClickListener {
            intent.putExtra(FLAG, FLAG_FLOWER)
            startActivity(intent)
        }

        punch_cantor_gasket.setOnClickListener {
            intent.putExtra(FLAG, FLAG_CANTOR_GASKET)
            startActivity(intent)
        }

        dragon_curve.setOnClickListener {
            intent.putExtra(FLAG, FLAG_DRAGON_CURVE)
            startActivity(intent)
        }

        stick_figure.setOnClickListener {
            intent.putExtra(FLAG, FLAG_STICK_FIGURE)
            startActivity(intent)
        }
    }

}