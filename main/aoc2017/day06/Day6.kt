package aoc2017.day06

import aoc2016.day12.memory

data class Memory (val registers: List<Int>) {
    fun next(): Memory {

        val ordered = registers.withIndex().sortedWith(compareByDescending<IndexedValue<Int>>({ it.value }).then(compareBy({it.index})))

        val transferReg = ordered[0].index
        val transferValue = ordered[0].value

        val adjusted : MutableList<Int> = mutableListOf()

        for (v in registers.withIndex()) {
            if (v.index != transferReg) {
                adjusted += v.value
            } else
            {
                adjusted += 0
            }
        }

        // Redistribute
        var targetRegister = (transferReg+1) % registers.size
        for (i in transferValue downTo 1) {
            adjusted[targetRegister]++
            targetRegister = (targetRegister+1) % registers.size
        }

        return Memory(adjusted)
    }
}

fun main(args: Array<String>) {

    var steps = 0
    val seenMemory : MutableMap<Memory,Int> = mutableMapOf()

    //val initial = listOf(0, 2, 7, 0)

    val initial = listOf(2,	8,	8,	5,	4,	2,	3,	1,	5,	5,	1,	2,	15,	13,	5,	14)

    var currentMemory : Memory = Memory(initial)
    seenMemory += Pair(currentMemory, steps)

    var done=false
    while (!done) {
        currentMemory = currentMemory.next()
        println(currentMemory)

        steps++

        if (seenMemory.containsKey(currentMemory)) {
            break
        }

        seenMemory += Pair(currentMemory, steps)
    }

    println(steps)
    println(steps- seenMemory[currentMemory] as Int)

}
