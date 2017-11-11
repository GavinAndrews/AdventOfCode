package aoc2016.day08

import java.io.File

class Screen(val width:Int, val height:Int) {

    var pixels: MutableList<MutableList<Boolean>> = mutableListOf()

    init {
        for (y in 0 until height) {
            val l : MutableList<Boolean> = mutableListOf()
            for (x in 0 until width) {
                l.add(false)
            }
            pixels.add(l)
        }
    }

    fun litPixels() : Int {
        var count=0

        for (y in 0 until height) {
            for (x in 0 until width) {
                if (pixels[y][x]) count++
            }
        }
        return count
    }


    fun render() {
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (pixels[y][x]) print("#") else print(" ")
            }
            println()
        }
        println("Lit: ${litPixels()}")
    }

    fun rect(xl:Int, yl:Int) {
        for (y in 0 until yl) {
            for (x in 0 until xl) {
                pixels[y][x] = true
            }
        }
    }

    fun rotateRow(rowY:Int) {
        val v = pixels[rowY][width-1]
        for (x in (width-1) downTo 1) {
            pixels[rowY][x] = pixels[rowY][x-1]
        }
        pixels[rowY][0]=v
    }

    fun rotateRow(rowY:Int, by:Int) {
        for (i in 1..by) {
            rotateRow(rowY)
        }
    }

    fun rotateCol(colX:Int) {
        val v = pixels[height-1][colX]
        for (y in (height-1) downTo 1)  {
            pixels[y][colX] = pixels[y-1][colX]
        }
        pixels[0][colX]=v
    }

    fun rotateCol(colX:Int, by:Int) {
        for (i in 1..by) {
            rotateCol(colX)
        }
    }
}



fun main(args: Array<String>) {
//    val text = File("src/Day25/bonus_result.txt").readLines()
//    val text = File("src/day8/example.txt").readLines()
//    val screen = Screen(7,3)
//    val text = File("src/day8/input.txt").readLines()
//    val screen = Screen(50,6)

    val text = File("src/Day25/bonus_result.txt").readLines()
    val screen = Screen(50,6)

    for (s in text) {
        processInstruction(screen, s)
        screen.render()
    }
}

fun  processInstruction(screen:Screen, s: String) {
    if (s.startsWith("rect")) {
        // rect 1x1
        val regex = Regex("""rect (\d+)x(\d+)""")
        val matchEntire1 = regex.matchEntire(s)
        val x = matchEntire1!!.groupValues[1]
        val y = matchEntire1.groupValues[2]
        println("RECT $x $y")
        screen.rect(x.toInt(),y.toInt())
    }
    else if (s.startsWith("rotate row"))
    {
        // rotate row y=0 by 2
        val regex = Regex("""rotate row y=(\d+) by (\d+)""")
        val matchEntire1 = regex.matchEntire(s)
        val y = matchEntire1!!.groupValues[1]
        val by = matchEntire1.groupValues[2]
        println("RR   $y by $by")
        screen.rotateRow(y.toInt(), by.toInt())
    } else if (s.startsWith("rotate column")) {
        // rotate column x=17 by 2
        val regex = Regex("""rotate column x=(\d+) by (\d+)""")
        val matchEntire1 = regex.matchEntire(s)
        val x = matchEntire1!!.groupValues[1]
        val by = matchEntire1.groupValues[2]
        println("RC   $x by $by")
        screen.rotateCol(x.toInt(), by.toInt())
    } else
    {
        println("TROUBLE AT MILL")
    }
}
