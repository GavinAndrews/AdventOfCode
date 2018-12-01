package aoc2015.day02

import org.junit.Assert
import org.junit.Test

class Day2Test {

    @Test
    fun testExample() {
        val (paper, ribbon) = solveFile("main/aoc2015/day02/example.txt")
        Assert.assertEquals(paper, 101)
        Assert.assertEquals(ribbon, 48)
    }

    @Test
    fun testPart1() {
        val (paper, ribbon) = solveFile("main/aoc2015/day02/input.txt")
        Assert.assertEquals(paper, 1606483)
        Assert.assertEquals(ribbon, 3842356)
    }
}
