package aoc2015.day01

import java.io.File

fun processMove(c: Char, current: Int): Int {
    if (c == '(') return current + 1
    if (c == ')') return current - 1
    return current
}

fun solveFile(fname: String): Pair<Int, Int?> {
    val input = File(fname).readText()
    return solve(input = input)
}

fun solve(input: String): Pair<Int, Int?> {

    var firstBasement: Int? = null

    var floor = 0

    for ((index, c) in input.withIndex()) {
        floor = processMove(c, floor)
        if (floor == -1) {
            if (firstBasement == null) firstBasement = index + 1
        }
    }

    return Pair(floor, firstBasement)
}


