package aoc2017.day05

import java.io.File

enum class Part {
    ONE, TWO
}

fun solveFile(fname: String, part: Part = Part.ONE): Int {

    val memory : MutableList<Int> = mutableListOf()
    var pc = 0

    val lines = File(fname).readLines()

    lines.mapTo(memory) { it.toInt() }

    var steps = 0

    // Execute Program
    while ((pc>=0)&&(pc<memory.size)) {
        val current = memory[pc]
        memory[pc] += if ((part==Part.TWO)&&(current>=3)) -1 else 1
        pc += current
        steps++
    }

    return steps
}
