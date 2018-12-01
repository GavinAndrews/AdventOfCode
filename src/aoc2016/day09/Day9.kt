package aoc2016.day09

import java.io.File

fun solve(fname: String, version: Int = 1): List<Long> {

    val results: MutableList<Long> = mutableListOf()

    val lines = File(fname).readLines()
    for (l in lines) {
        val result = expand(l, version)
        results.add(result)
        println(result)

    }

    return results
}

fun expand(line: String, version: Int): Long {

    var remaining = line

    var count = 0L

    while (remaining.isNotEmpty()) {
        if (remaining[0] != '(') {
            count++
            remaining = remaining.substring(1)
        } else {
            // Extract Operation
            val reg = Regex("""\((\d+)x(\d+)\)(.*)""")
            val matches = reg.matchEntire(remaining)
            val a = matches!!.groups[1]!!.value.toInt()
            val b = matches.groups[2]!!.value.toInt()

            val other = matches.groups[3]!!.value

            @Suppress("LiftReturnOrAssignment")
            if (version == 1) {
                count += a * b
            } else {
                val expanding = other.substring(0, a)
                val subcount = expand(expanding, version)
                count += subcount * b
            }
            remaining = other.substring(a)
        }
    }
    return count
}