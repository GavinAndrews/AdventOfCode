package aoc2016.day03

import org.junit.Assert
import org.junit.Test

class Day3Test {
    @Test
    fun solveDay3Part1() {
        val result = solveDay3("main/aoc2016/day03/input.txt",true)
        Assert.assertEquals(983, result)
    }
    @Test
    fun solveDay3Part2() {
        val result = solveDay3("main/aoc2016/day03/input.txt",false)
        Assert.assertEquals(1836, result)
    }
}
