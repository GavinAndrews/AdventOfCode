package aoc2016.day06

import org.junit.Assert
import org.junit.Test

class Day6Test {
    @Test
    fun solveDay6Part1() {
        val result = solveDay6("main/aoc2016/day06/input.txt",true)
        Assert.assertEquals("tzstqsua", result)
    }
    @Test
    fun solveDay6Part2() {
        val result = solveDay6("main/aoc2016/day06/input.txt",false)
        Assert.assertEquals("myregdnr", result)
    }
}