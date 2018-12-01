package aoc2016.day01

import org.junit.Assert
import org.junit.Test
import java.io.File

class Day1Test {

    @Test
    fun testExample() {

        val lines = File("main/aoc2016/day01/example.txt").readLines()
        val d = processInstructionSequence(lines[0],false)
        Assert.assertEquals(12, d)
    }

    @Test
    fun testPart1() {

        val lines = File("main/aoc2016/day01/input.txt").readLines()
        val d = processInstructionSequence(lines[0],false)
        Assert.assertEquals(161, d)
    }

    @Test
    fun testPart2() {

        val lines = File("main/aoc2016/day01/input.txt").readLines()
        val d = processInstructionSequence(lines[0],true)
        Assert.assertEquals(110, d)
    }

}