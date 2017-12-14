package aoc2017.day10

import org.junit.Assert
import org.junit.Test

class Day10Test {

    @Test
    fun testExamplePart1() {
        Assert.assertEquals(12, solveFile("main/aoc2017/day10/example.txt", 5, Part.ONE))
    }

    @Test
    fun testPart1() {
        Assert.assertEquals(19591, solveFile("main/aoc2017/day10/input.txt", 256, Part.ONE))
    }

    @Test
    fun testExamplePart2() {
        Assert.assertEquals("a2582a3a0e66e6e86e3812dcb672a272", solve("", 256, Part.TWO))
        Assert.assertEquals("33efeb34ea91902bb2f59c9920caa6cd", solve("AoC 2017", 256, Part.TWO))
        Assert.assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", solve("1,2,3", 256, Part.TWO))
        Assert.assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", solve("1,2,4", 256, Part.TWO))
    }

    @Test
    fun testPart2() {
        Assert.assertEquals("62e2204d2ca4f4924f6e7a80f1288786", solveFile("main/aoc2017/day10/input.txt", 256, Part.TWO))
    }
}