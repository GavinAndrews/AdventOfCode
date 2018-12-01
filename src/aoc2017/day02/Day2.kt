package aoc2017.day02

import java.io.File

enum class Part {
    ONE, TWO
}

data class Position(val x:Int, val y:Int)

fun solveFile(fname: String, part: Part = Part.ONE): Int {

    val lines = File(fname).readLines()

    val grid : MutableMap<Position, Int> = mutableMapOf()

    var y=0

    for (line in lines) {
        val bits = line.split("\t".toRegex()).map(String::toInt)
        for (x in 0 until bits.size) {
            grid[Position(x,y)] = bits[x]
        }
        y++
    }

    val gridSize = y

    return if (part==Part.ONE) part1(gridSize, grid) else part2(gridSize, grid)
}

private fun part2(gridSize: Int, grid: MutableMap<Position, Int>): Int {
    var total = 0

    for (row in 0 until gridSize) {
        val list : MutableList<Int> = mutableListOf()
        for (col in 0 until gridSize) {
            list += grid[Position(col, row)] as Int
        }

        list.sortDescending()

        var found : Int? = null

        for ((i1,e1) in list.withIndex()) {
            for ((i2,e2) in list.withIndex()) {
                if (i1==i2) continue

                if (((e1/e2)*e2 == e1)||((e2/e1)*e1 == e2)) {
                    found = if (e1>e2) e1/e2 else e2/e1
                    break
                }

            }
            if (found != null) break
        }

        total += found!!
    }

    return total
}


private fun part1(gridSize: Int, grid: MutableMap<Position, Int>): Int {
    var total = 0

    for (row in 0 until gridSize) {
        var smallest: Int? = null
        var largest: Int? = null
        for (col in 0 until gridSize) {
            val value: Int = grid[Position(col, row)] as Int
            if (smallest == null) {

            }
            if ((smallest == null) || (smallest > value)) smallest = value
            if ((largest == null) || (largest < value)) largest = value
        }
        val diff: Int = largest!! - smallest!!
        total += diff
    }
    return total
}