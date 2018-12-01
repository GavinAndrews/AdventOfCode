package aoc2018.day01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun testPart1() {
        assertEquals(solveFile("src/aoc2018/day01/input.txt"), 522)
    }

    @Test
    fun testPart2() {
        assertEquals(solveFile("src/aoc2018/day01/input.txt", Part.TWO), 73364)
    }
}