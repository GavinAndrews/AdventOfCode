package aoc2017.day03

import org.junit.Assert
import org.junit.Test

class Day3Test {

    @Test
    fun testPart1Example() {

        Assert.assertEquals(solve(1).first!!.distance(), 0)
        Assert.assertEquals(solve(12).first!!.distance(), 3)
        Assert.assertEquals(solve(23).first!!.distance(), 2)
        Assert.assertEquals(solve(1024).first!!.distance(), 31)
    }

    @Test
    fun testPart1() {
        Assert.assertEquals(solve(312051).first!!.distance(), 430)
    }

    @Test
    fun testPart2() {
        Assert.assertEquals(solve(312051).second, 312453)
    }



}