package aoc2016.day03

import java.io.File
import kotlin.coroutines.experimental.buildSequence

fun solveDay3(fname: String, horizontal: Boolean): Int {

    val text = File(fname).readLines()

    var good = 0

    val regex: Regex = Regex("\\s*(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*")

    val horizontalValues = buildSequence {
        for (line in text) {
            val matchEntire1 = regex.matchEntire(line)
            val parts1 = listOf(matchEntire1!!.groups[1]!!.value, matchEntire1.groups[2]!!.value, matchEntire1.groups[3]!!.value).map(String::toInt)
            for (p in parts1) {
                yield(p)
            }
        }
    }

    val horizontalIterator = horizontalValues.iterator()

    val verticalValues = buildSequence {
        while (horizontalIterator.hasNext()) {
            val triangle1 = listOf(horizontalIterator.next(), horizontalIterator.next(), horizontalIterator.next())
            val triangle2 = listOf(horizontalIterator.next(), horizontalIterator.next(), horizontalIterator.next())
            val triangle3 = listOf(horizontalIterator.next(), horizontalIterator.next(), horizontalIterator.next())
            for (i in 0..2) {
                yield(triangle1[i])
                yield(triangle2[i])
                yield(triangle3[i])
            }
        }
    }

    val valueIterator = if (horizontal) horizontalValues.iterator() else verticalValues.iterator()

    while (valueIterator.hasNext()) {
        val triangle = getThreeValues(valueIterator)
        //println(triangle)
        if (isValidTriangle(triangle)) {
            good++
        }
    }

    return good
}

private fun isValidTriangle(threeValues: List<Int>) : Boolean {
    val orderedValues = threeValues.sortedDescending()
    return orderedValues[0] < (orderedValues[1] + orderedValues[2])
}

private fun getThreeValues(iterator: Iterator<Int>) = listOf(iterator.next(), iterator.next(), iterator.next())
