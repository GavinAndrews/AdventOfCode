package aoc2017.day02

import org.junit.Assert
import org.junit.Test

class Day2Test {

    @Test
    fun testPart1() {
        Assert.assertEquals(solveFile("main/aoc2017/day02/input.txt", Part.ONE), 47623)
    }

    @Test
    fun testPart2() {
        Assert.assertEquals(solveFile("main/aoc2017/day02/input.txt", Part.TWO), 312)
    }

}
