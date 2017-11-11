package aoc2016.day06

import java.io.File

fun solveDay6(fname:String, mostCommon:Boolean) : String {

    val lines = File(fname).readLines()

    val columnSets : MutableList<MutableMap<Char, Int>> = mutableListOf()

    for (l in lines) {

        for (i in 0 until l.length) {
            if (columnSets.size<(i+1)) {
                columnSets.add(mutableMapOf<Char,Int>())
            }
            val columnSet = columnSets[i]
            val c = l[i]
            if (!columnSet.contains(c)) {
                columnSet.put(c, 0)
            }
            var count = columnSet.get(c) ?: 0
            count++
            columnSet.put(c, count)
        }
    }

    var result = ""

    for (cs in columnSets) {
        val sorted : List<Pair<Char, Int>>
        if (mostCommon) {
            sorted = cs.toList().sortedByDescending { it.second }
        } else
        {
            sorted = cs.toList().sortedBy { it.second }
        }

        result += sorted.first().first
    }

    return result
}