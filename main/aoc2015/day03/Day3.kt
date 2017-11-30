package aoc2015.day03

import java.io.File

data class Position(val x: Int, val y: Int) {
    fun positionAfterMove(c: Char): Position {
        when (c) {
            '^' -> return Position(x, y + 1)
            'v' -> return Position(x, y - 1)
            '<' -> return Position(x - 1, y)
            else -> return Position(x + 1, y)
        }
    }
}

fun solveFile(fname: String, sleighCount: Int = 1): Int {
    val input = File(fname).readText()
    return solve(input, sleighCount)
}

fun solve(input: String, sleighCount: Int = 1): Int {

    val initial = Position(0, 0)
    val visited: MutableMap<Position, Int> = mutableMapOf()

    for (sleigh in 1..sleighCount) {

        var current = initial

        increment(visited, current)

        for (i in sleigh - 1..input.length - 1 step sleighCount) {
            val c = input[i]
            current = current.positionAfterMove(c)
            increment(visited, current)
        }
    }

    return visited.size
}

private fun increment(visited: MutableMap<Position, Int>, current: Position) {
    var v: Int? = visited[current]
    v = v ?: 0
    v++
    visited[current] = v
}

