package aoc2016.day05

import org.junit.Assert
import org.junit.Test

class Day5Test {
    @Test
    fun solveDay5ExamplePart1() {
        val result = part1("abc")
        Assert.assertEquals("18f47a30", result.toLowerCase())
    }

    @Test
    fun solveDay5ExamplePart2() {
        val result = part2("abc")
        Assert.assertEquals("05ace8e3", result.toLowerCase())
    }

    @Test
    fun solveDay5Part1() {
        val result = part1("reyedfim")
        Assert.assertEquals("f97c354d", result.toLowerCase())
    }

    @Test
    fun solveDay5Part2() {
        val result = part2("reyedfim")
        Assert.assertEquals("863dde27", result.toLowerCase())
    }
}
