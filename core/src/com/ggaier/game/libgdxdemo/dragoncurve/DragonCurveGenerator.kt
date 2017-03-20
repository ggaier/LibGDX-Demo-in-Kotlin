package com.ggaier.game.libgdxdemo.dragoncurve

import com.badlogic.gdx.math.Vector2
import java.util.*

/**
 * Created by ggaier at 03/03/2017 .
 * jwenbo52@gmail.com
 */
class DragonCurveGenerator() {

    enum class Direction {
        LEFT, RIGHT;

        companion object {
            fun turn(heading: Vector2, turn: Direction): Vector2 {
                val newHeading = Vector2()
                when (turn) {
                    LEFT -> {
                        newHeading.x = -heading.y
                        newHeading.y = heading.x
                    }
                    RIGHT -> {
                        newHeading.x = heading.y
                        newHeading.y = -heading.x
                    }
                }
                return newHeading
            }
        }

    }

    companion object {

        fun dragonTurns(recursions: Int): LinkedList<Direction> {
            val turns = LinkedList<Direction>()
            turns.add(Direction.LEFT)

            for (i in 1..recursions) {
                //首先把turns中的方向全部反转
                val reversed: LinkedList<Direction> = LinkedList()
                reversed.addAll(turns)
                Collections.reverse(reversed)

                //添加一个左转
                turns.add(Direction.LEFT)

                // 对颠倒方向后的进行方向反射
                for (turn in reversed) {
                    when (turn) {
                        Direction.LEFT -> turns.add(Direction.RIGHT)
                        Direction.RIGHT -> turns.add(Direction.LEFT)
                    }
                }

            }
            return turns
        }

        fun generateDragonCurve(width: Int, height: Int, recursions: Int):FloatArray {
            val turns = DragonCurveGenerator.dragonTurns(recursions)
            val head = Vector2(width / 2.toFloat(), height / 2.toFloat())
            var heading = Vector2(20f, 0f)

            val curve = FloatArray((turns.size + 1) * 2)

            var i = 0
            curve[i++] = head.x
            curve[i++] = head.y

            for (turn in turns) {
                heading = Direction.turn(heading, turn)
                head.x += heading.x
                head.y += heading.y
                curve[i++]=head.x
                curve[i++]=head.y
            }
            return curve
        }

    }

}