package aoc2018.day03

import java.io.File

class Location(private val x: Int, private val y: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Location

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}

fun solveFile(fname: String): Int {

    val lines = File(fname).readLines()


    val regex = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()

    val locations = mutableMapOf<Location, MutableList<Int>>()

    for (line in lines) {

      val matchResult = regex.find(line)
        val parts = matchResult?.groupValues
        val number = parts!![1].toInt()
        val x = parts!![2].toInt()
        val y = parts!![3].toInt()
        val w = parts!![4].toInt()
        val h = parts!![5].toInt()

        for (i in 0 until w) {
            for (j in 0 until h) {
                val p = x + i
                val q = y + j

                val l = Location(p,q)
                if (locations.containsKey(l))
                {
                    locations[l]!!.add(number)
                } else {
                    locations[l] = mutableListOf(number)
                }
            }
        }
    }

    val collisions = locations.filterValues { v -> v.size>1 }
    println(collisions.size)

    for (line in lines) {

        val matchResult = regex.find(line)
        val parts = matchResult?.groupValues
        val number = parts!![1].toInt()
        val x = parts!![2].toInt()
        val y = parts!![3].toInt()
        val w = parts!![4].toInt()
        val h = parts!![5].toInt()

        var ok=true

        for (i in 0 until w) {
            for (j in 0 until h) {
                val p = x + i
                val q = y + j

                val l = Location(p, q)
                val occupants = locations[l]
                if (occupants!![0] != number || occupants.size>1) {
                    ok = false
                }
            }
        }

        if (ok) {
            println(number)
        }
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
