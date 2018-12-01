package aoc2016.day14

import org.junit.Assert
import org.junit.Test

class Day14Test {

    @Test
    fun solveDay14ExamplePart1() {
        val result = solve("abc", false)
        Assert.assertEquals(22728L, result)
    }

    @Test
    fun solveDay14ExamplePart2() {
        val result = solve("abc", true)
        Assert.assertEquals(22551L, result)
    }

    @Test
    fun solveDay14Part1() {
        val result = solve("zpqevtbw", false)
        Assert.assertEquals(16106L, result)
    }

    @Test
    fun solveDay14Part2() {
        val result = solve("zpqevtbw", true)
        Assert.assertEquals(22423L, result)
    }
}