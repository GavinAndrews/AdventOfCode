package aoc2016.day16

import org.junit.Assert
import org.junit.Test

class Day16Test {

    @Test
    fun solveDay16Part1() {
        val solution = solve("10010000000110000",272)
        Assert.assertEquals("10010110010011110", solution)
    }

    @Test
    fun solveDay16Part2() {
        val solution = solve("10010000000110000",35651584)
        Assert.assertEquals("01101011101100011", solution)
    }
}