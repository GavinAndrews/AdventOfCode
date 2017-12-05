package aoc2017.day04

import java.io.File

enum class Part {
    ONE, TWO
}


fun solveFile(fname: String, part: Part = Part.ONE): Int {

    val lines = File(fname).readLines()

    var total = 0

    for (line in lines) {

        var dup = false

        val words : MutableSet<String> = mutableSetOf()

        val bits = line.split("""\s""".toRegex())

        for (bit in bits) {

            val bitAsCharArray = bit.toCharArray()

            if (part==Part.TWO) bitAsCharArray.sort()

            val adjustedWord = String(bitAsCharArray)

            if (words.contains(adjustedWord)) {
                dup = true
            } else
            {
                words.add(adjustedWord)
            }
        }

        if (!dup) total++
    }

    return total

}