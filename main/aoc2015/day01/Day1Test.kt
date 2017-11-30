package aoc2015.day01

import org.junit.Assert
import org.junit.Test

class Day1Test {

    @Test
    fun testExamples() {
        Assert.assertEquals(solve("(())").first, 0)
        Assert.assertEquals(solve("()()").first, 0)
        Assert.assertEquals(solve("(((").first, 3)
        Assert.assertEquals(solve("(()(()(").first, 3)
        Assert.assertEquals(solve("))(((((").first, 3)
        Assert.assertEquals(solve("())").first, -1)
        Assert.assertEquals(solve("))(").first, -1)
        Assert.assertEquals(solve(")))").first, -3)
        Assert.assertEquals(solve(")())())").first, -3)
    }

    @Test
    fun testPart1() {
        val (floor, _) = solveFile("main/aoc2015/day01/input.txt")
        Assert.assertEquals(floor, 138)
    }

    @Test
    fun testPart2() {
        val (_, basement) = solveFile("main/aoc2015/day01/input.txt")
        Assert.assertEquals(basement, 1771)
    }
}
