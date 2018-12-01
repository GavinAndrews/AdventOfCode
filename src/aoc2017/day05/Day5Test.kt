package aoc2017.day05

import org.junit.Assert
import org.junit.Test

class Day5Test {

    @Test
    fun testPart1Example() {
        Assert.assertEquals(5, solveFile("main/aoc2017/day05/example.txt", Part.ONE))
    }

    @Test
    fun testPart1() {
        Assert.assertEquals(358309, solveFile("main/aoc2017/day05/input.txt", Part.ONE))
    }

    @Test
    fun testPart2Example() {
        Assert.assertEquals(10, solveFile("main/aoc2017/day05/example.txt", Part.TWO))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals(28178177, solveFile("main/aoc2017/day05/input.txt", Part.TWO))
    }

}
