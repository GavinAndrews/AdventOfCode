package aoc2016.day09

import org.junit.Assert
import org.junit.Test

class Day9Test {
    @Test
    fun solveDay9ExamplePart1() {
        val results = solve("main/aoc2016/day09/example.txt", 1)
        Assert.assertEquals(listOf(6L, 7L, 9L, 11L, 6L, 18L, 324L, 238L), results)
    }

    @Test
    fun solveDay9ExamplePart2() {
        val results = solve("main/aoc2016/day09/example.txt", 2)
        Assert.assertEquals(listOf(6L, 7L, 9L, 11L, 3L, 20L, 241920L, 445L), results)
    }

    @Test
    fun solveDay9Part2() {
        val results = solve("main/aoc2016/day09/input.txt", 1)
        Assert.assertEquals(138735, results[0])
    }

    @Test
    fun solveDay9Part1() {
        val results = solve("main/aoc2016/day09/input.txt", 2)
        Assert.assertEquals(11125026826, results[0])
    }
}