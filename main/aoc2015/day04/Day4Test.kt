package aoc2015.day04

import org.junit.Assert
import org.junit.Test
import java.time.LocalDateTime

class Day4Test {

    @Test
    fun testExamples() {
        Assert.assertEquals(solve("abcdef"), 609043)
        Assert.assertEquals(solve("pqrstuv"), 1048970 )
    }

    @Test
    fun testPart1() {
        Assert.assertEquals(solve("ckczppom"), 117946)
    }

    @Test
    fun testPart2() {
        println("START: ${LocalDateTime.now()}")
        Assert.assertEquals(solve("ckczppom","000000"), 3938038)
        println("STOP : ${LocalDateTime.now()}")
    }
}