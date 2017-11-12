package aoc2016.day12

import org.junit.Assert
import org.junit.Test

class Day12Test {

    @Test
    fun solveDay12Part1() {
        val registers = solve("main/aoc2016/day12/input.txt")
        Assert.assertEquals(318007, registers["a"])
    }

    @Test
    fun solveDay12Part2() {
        val initial = mapOf<String, Int>(Pair("c",1))
        val registers = solve("main/aoc2016/day12/input.txt", initial)
        Assert.assertEquals(9227661, registers["a"])
    }
}