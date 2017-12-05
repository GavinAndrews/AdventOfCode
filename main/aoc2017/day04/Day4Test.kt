package aoc2017.day04

import org.junit.Assert
import org.junit.Test

class Day2Test {

    @Test
    fun testPart1Example() {
        Assert.assertEquals(solveFile("main/aoc2017/day04/example.txt", Part.ONE), 2)
    }

    @Test
    fun testPart1() {
        Assert.assertEquals(solveFile("main/aoc2017/day04/input.txt", Part.ONE), 337)
    }

    @Test
    fun testPart2() {
        Assert.assertEquals(solveFile("main/aoc2017/day04/input.txt", Part.TWO), 231)
    }

}
