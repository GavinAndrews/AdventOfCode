package aoc2015.day03

import org.junit.Assert
import org.junit.Test

class Day3Test {

    @Test
    fun testExamples() {
        Assert.assertEquals(solve(">"), 2)
        Assert.assertEquals(solve("^>v<"), 4)
        Assert.assertEquals(solve("^v^v^v^v^v"), 2)
    }

    @Test
    fun testPart1() {
        Assert.assertEquals(solveFile("main/aoc2015/day03/input.txt"), 2572)
    }

    @Test
    fun testPart2() {
        Assert.assertEquals(solveFile("main/aoc2015/day03/input.txt", 2), 2631)
    }

}
