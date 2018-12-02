package aoc2018.day02

import java.io.File

fun solveFile(fname: String): Int {

    val lines = File(fname).readLines()

    var doubleCount = 0
    var tripleCount = 0

    for (l in lines) {

        val charsMap = mutableMapOf<Char, Int>()

        l.forEach {
            charsMap[it] = charsMap.getOrDefault(it, 0) + 1
        }

        val doubles = charsMap.filter { e -> e.value == 2 }
        val triples = charsMap.filter { e -> e.value == 3 }

        val isDouble = !doubles.isEmpty()
        if (isDouble) doubleCount++

        val isTriple = !triples.isEmpty()
        if (isTriple) tripleCount++
    }

    return doubleCount * tripleCount
}

fun solveFile2(fname: String): String {

    val lines = File(fname).readLines()

    for (l1 in lines) {
        for (l2 in lines) {
            if (l1 == l2) continue
            val matches = mutableListOf<Boolean>()
            var offset = 0
            for ((index, value1) in l1.withIndex()) {
                val value2 = l2[index]
                val match = value1 == value2
                if (!match) offset = index
                matches.add(match)
            }

            val misMatches = matches.filter { e -> !e }
            if (misMatches.size == 1) {
                var s = l1.substring(0, offset)
                s += l1.substring(offset + 1)
                return s
            }
        }
    }


    return "OK"
}
