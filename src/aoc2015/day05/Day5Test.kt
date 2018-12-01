package aoc2015.day05

import org.junit.Assert
import org.junit.Test

class Day5Test {

    @Test
    fun testPart1Examples() {
        Assert.assertEquals(solve("ugknbfddgicrmopn"), true)
        Assert.assertEquals(solve("aaa"), true)
        Assert.assertEquals(solve("jchzalrnumimnmhp"), false)
        Assert.assertEquals(solve("haegwjzuvuyypxyu"), false)
        Assert.assertEquals(solve("dvszwmarrgswjxmb"), false)
    }

    @Test
    fun testPart1() {
        Assert.assertEquals(solveFile("main/aoc2015/day05/input.txt"), 238)
    }

    @Test
    fun testPart2Examples() {
        Assert.assertEquals(solve("qjhvhtzxzqqjkmpb", ::part2NicenessTest), true)
        Assert.assertEquals(solve("xxyxx", ::part2NicenessTest), true)
        Assert.assertEquals(solve("uurcxstgmygtbstg", ::part2NicenessTest), false)
        Assert.assertEquals(solve("ieodomkazucvgmuy", ::part2NicenessTest), false)
    }

    @Test
    fun testPart2() {
        Assert.assertEquals(solveFile("main/aoc2015/day05/input.txt", ::part2NicenessTest), 69)
    }
}