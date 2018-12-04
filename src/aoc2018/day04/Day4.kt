package aoc2018.day04

import java.io.File

fun solveFile(fname: String): Int {

    val lines = File(fname).readLines()


    val regex1 = """\[1518-..-.. ..:..\] Guard #(\d+) begins shift""".toRegex()
    val regex2 = """\[1518-..-.. ..:(\d+)\] (.)""".toRegex()

    var awake: IntArray? = null
    var sleepStart: Int? = null
    var currentGuard: Int? = null

    class Result(private val number: Int, private val awake: IntArray) {
    }

    var results = mutableListOf<Result>()

    for (line in lines) {

        println(line)
        val matchResult = regex1.find(line)
        if (matchResult != null) {

            if (currentGuard != null) {
                // Tidy up from last Guard
                if (sleepStart != null) {
                    for (min in sleepStart!!..59) {
                        awake!![min] = 0
                    }
                }
                results.add(Result(currentGuard!!, awake!!))
            }

            val parts = matchResult?.groupValues
            val number = parts!![1].toInt()
            awake = IntArray(60, init = { i -> 1 })
            currentGuard = number
        } else {
            val matchResult2 = regex2.find(line)
            if (matchResult2 != null) {
                val parts2 = matchResult2?.groupValues
                val min = parts2!![1].toInt()
                val fallsAsleep = "f".equals(parts2!![2])
                if (fallsAsleep) {
                    sleepStart = min
                } else {
                    for (min in sleepStart!!..min) {
                        awake!![min] = 0
                    }
                    sleepStart = null
                }
            }
        }

        // Last one?
        if (currentGuard != null) {
            // Tidy up from last Guard
            if (sleepStart != null) {
                for (min in sleepStart!!..59) {
                    awake!![min] = 0
                }
            }
            results.add(Result(currentGuard!!, awake!!))
        }

        println("Done")

    }

    return 0
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
