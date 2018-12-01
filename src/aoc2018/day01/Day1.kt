package aoc2018.day01

import java.io.File

enum class Part {
    ONE, TWO
}

fun solveFile(fname: String, part: Part = Part.ONE): Int {

    val lines = File(fname).readLines()
    val seen = HashSet<Int>()

    var freq = 0
    seen.add(freq)

    while (true) {

        for (line in lines) {
            val value = line.toInt()
            freq += value
            if (seen.contains(freq)) {
                if (part == Part.TWO) return freq
            }
            seen.add(freq)
        }

        if (part == Part.ONE) return freq
    }
}
