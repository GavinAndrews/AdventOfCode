package aoc2017.day01

import java.io.File

enum class Part {
    ONE, TWO
}

fun solveFile(fname: String, part:Part = Part.ONE): Int {
    val line = File(fname).readText()
    return solve(line, part)
}

fun extractorPrevious(input:String, index:Int) : Char {
    return input[(index+input.length+1) % input.length]
}

fun extractorHalfWayAlong(input:String, index:Int) : Char {
    return input[(index+input.length/2) % input.length]
}

fun solve(input: String, part:Part = Part.ONE): Int {

    val fn: (String, Int) -> Char = if (part==Part.ONE) ::extractorPrevious else ::extractorHalfWayAlong

    var total = 0

    for ((i,c) in input.withIndex()) {
        val comparison = fn(input,i)
        if (c==comparison) {
            total += c.toString().toInt()
        }
    }

    return total
}

