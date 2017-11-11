package aoc2016.day24

import org.junit.Assert
import org.junit.Test

class Day24Test {

    @Test
    fun solveDay24ExamplePart1() {
        val result = loadMap(false)
        Assert.assertEquals(462, result)
    }

    @Test
    fun solveDay24ExamplePart2() {
        val result = loadMap(true)
        Assert.assertEquals(676, result)
    }
}