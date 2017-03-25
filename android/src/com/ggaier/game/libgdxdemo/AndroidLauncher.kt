package com.ggaier.game.libgdxdemo

import android.os.Bundle
import android.widget.Toast
import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.ggaier.game.libgdxdemo.cantorgasket.CantorGasket
import com.ggaier.game.libgdxdemo.cyclicoverlap.CyclicOverlap
import com.ggaier.game.libgdxdemo.dots.ConnectTheDots
import com.ggaier.game.libgdxdemo.dragoncurve.DragonCurve
import com.ggaier.game.libgdxdemo.flower.RectangleFlower
import com.ggaier.game.libgdxdemo.icicles.IciclesGame
import com.ggaier.game.libgdxdemo.movement.CirculationMotion
import com.ggaier.game.libgdxdemo.movement.ReciprocatingMotion
import com.ggaier.game.libgdxdemo.movement.applicationadaptertogame.MyGame
import com.ggaier.game.libgdxdemo.movement.fallingobjects.FallingObjectsGame
import com.ggaier.game.libgdxdemo.movement.fpscounter.FPSCounterGame
import com.ggaier.game.libgdxdemo.movement.screensaver.ScreenSaver
import com.ggaier.game.libgdxdemo.orthographiccamera.OrthographicCamera
import com.ggaier.game.libgdxdemo.sierpinskitriangle.SierpinskiTriangle
import com.ggaier.game.libgdxdemo.smileyface.SmileyFace
import com.ggaier.game.libgdxdemo.spirals.DrawASpiral
import com.ggaier.game.libgdxdemo.stickfigure.DrawAStickFigure
import com.ggaier.game.libgdxdemo.userinput.accelerometer.BubbleLevelGame
import com.ggaier.game.libgdxdemo.userinput.accelerometermovement.AccelerometerBallGame
import com.ggaier.game.libgdxdemo.userinput.inputtestbed.InputTestBed
import com.ggaier.game.libgdxdemo.viewports.ViewportsExercise
import com.ggaier.game.libgdxdemo.worldcloud.WorldCloud

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
            FLAG_BOUNCE_BALL_WITH_INPUT -> applicationListener = InputTestBed()
            FLAG_BUBBLE_LEVEL -> applicationListener = BubbleLevelGame()
            FLAG_BOUNCE_BALL_WITH_ACCELERATOR -> applicationListener = AccelerometerBallGame()
            FLAG_ICICLES_GAME -> applicationListener = IciclesGame()
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