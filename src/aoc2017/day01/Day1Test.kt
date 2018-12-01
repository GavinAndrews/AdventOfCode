package aoc2017.day01

import org.junit.Assert
import org.junit.Test

class Day1Test {

    @Test
    fun testPart1Examples() {
        Assert.assertEquals(solve("1122"), 3)
        Assert.assertEquals(solve("1111"), 4)
        Assert.assertEquals(solve("1234"), 0)
        Assert.assertEquals(solve("91212129"), 9)
    }

    @Test
    fun testPart1() {
        Assert.assertEquals(solveFile("main/aoc2017/day01/input.txt"), 1343)
    }

    @Test
    fun testPart2Examples() {
        Assert.assertEquals(solve("1212", Part.TWO), 6)
        Assert.assertEquals(solve("1221", Part.TWO), 0)
        Assert.assertEquals(solve("123425", Part.TWO), 4)
        Assert.assertEquals(solve("123123", Part.TWO), 12)
        Assert.assertEquals(solve("12131415", Part.TWO), 4)
    }

    @Test
    fun testPart2() {
        Assert.assertEquals(solveFile("main/aoc2017/day01/input.txt", Part.TWO), 1274)
    }
}