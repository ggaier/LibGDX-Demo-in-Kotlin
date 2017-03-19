package com.ggaier.libgdxkotlin

import android.os.Bundle
import android.widget.Toast
import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.ggaier.gamedev.StartFiled
import com.ggaier.gamedev.cantorgasket.CantorGasket
import com.ggaier.gamedev.cyclicoverlap.CyclicOverlap
import com.ggaier.gamedev.dots.ConnectTheDots
import com.ggaier.gamedev.dragoncurve.DragonCurve
import com.ggaier.gamedev.flower.RectangleFlower
import com.ggaier.gamedev.movement.CirculationMotion
import com.ggaier.gamedev.movement.ReciprocatingMotion
import com.ggaier.gamedev.movement.applicationadaptertogame.MyGame
import com.ggaier.gamedev.movement.fallingobjects.FallingObjectsGame
import com.ggaier.gamedev.movement.fpscounter.FPSCounterGame
import com.ggaier.gamedev.movement.screensaver.ScreenSaver
import com.ggaier.gamedev.orthographiccamera.OrthographicCamera
import com.ggaier.gamedev.sierpinskitriangle.SierpinskiTriangle
import com.ggaier.gamedev.smileyface.SmileyFace
import com.ggaier.gamedev.spirals.DrawASpiral
import com.ggaier.gamedev.stickfigure.DrawAStickFigure
import com.ggaier.gamedev.viewports.ViewportsExercise
import com.ggaier.gamedev.worldcloud.WorldCloud

/**
 * Created by ggaier at 27/02/2017 .
 * jwenbo52@Gmail.com
 */
class AndroidLauncher : AndroidApplication() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val flag = intent.getIntExtra(FLAG, FLAG_START_FILED)
        var applicationListener: ApplicationListener? = null
        when (flag) {
            FLAG_START_FILED -> applicationListener = StartFiled()
            FLAG_CONNECT_DOTS -> applicationListener = ConnectTheDots()
            FLAG_SPIRAL -> applicationListener = DrawASpiral()
            FLAG_FLOWER -> applicationListener = RectangleFlower()
            FLAG_CANTOR_GASKET -> applicationListener = CantorGasket()
            FLAG_DRAGON_CURVE -> applicationListener = DragonCurve()
            FLAG_STICK_FIGURE -> applicationListener = DrawAStickFigure()
            FLAG_ORTHOGRAPHIC_CAMERA -> applicationListener = OrthographicCamera()
            FLAG_VIEWPORTS_EXERCISE -> applicationListener = ViewportsExercise()
            FLAG_SMILEY_FACE -> applicationListener = SmileyFace()
            FLAG_WORLD_CLOUD -> applicationListener = WorldCloud()
            FLAG_CYCLIC_OVERLAP -> applicationListener = CyclicOverlap()
            FLAG_SIERPINSKI_TRIANGLE -> applicationListener = SierpinskiTriangle()
            FLAG_FANCY_CIRCULATION -> applicationListener = CirculationMotion()
            FLAG_RECIPROCATING_MOTION -> applicationListener = ReciprocatingMotion()
            FLAG_ADAPTER_TO_GAME -> applicationListener = MyGame()
            FLAG_FPS_SCREEN -> applicationListener = FPSCounterGame()
            FLAG_FALLING_OBJECTS -> applicationListener = FallingObjectsGame()
            FLAG_BOUNCE_BALL -> applicationListener = ScreenSaver()
            else -> {
                Toast.makeText(this@AndroidLauncher, "Wrong Drawing Type",
                        Toast.LENGTH_LONG).show()
            }
        }
        val androidApplicationConfig = AndroidApplicationConfiguration()
        if (applicationListener != null) {
            initialize(applicationListener, androidApplicationConfig)
        }
    }


}