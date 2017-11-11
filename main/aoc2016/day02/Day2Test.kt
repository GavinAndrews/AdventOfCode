package aoc2016.day02

import org.junit.Assert
import org.junit.Test
import java.io.File

class Day2Test {

    @Test
    fun testExamplePart1() {
        val result = scenario1("main/aoc2016/day02/example.txt")
        Assert.assertEquals("1985", result)
    }
    @Test
    fun testExamplePart2() {
        val result = scenario2("main/aoc2016/day02/example.txt")
        Assert.assertEquals("5DB3", result)
    }

    @Test
    fun testPart1() {
        val result = scenario1("main/aoc2016/day02/input.txt")
        Assert.assertEquals("18843", result)
    }
    @Test
    fun testPart2() {
        val result = scenario2("main/aoc2016/day02/input.txt")
        Assert.assertEquals("67BB9", result)
    }
}