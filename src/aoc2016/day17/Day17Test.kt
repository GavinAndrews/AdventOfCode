package aoc2016.day17

import org.junit.Assert
import org.junit.Test

class Day17Test {

    @Test
    fun solveDay17Part1Example1() {
        val solution = solve("ihgpwlah", true)
        Assert.assertEquals("DDRRRD", solution!!.path)
    }

    @Test
    fun solveDay17Part1Example2() {
        val solution = solve("kglvqrro",true)
        Assert.assertEquals("DDUDRLRRUDRD", solution!!.path)
    }

    @Test
    fun solveDay17Part1Example3() {
        val solution = solve("ulqzkmiv",true)
        Assert.assertEquals("DRURDRUDDLLDLUURRDULRLDUUDDDRR", solution!!.path)
    }

    @Test
    fun solveDay17Part1() {
        val solution = solve("veumntbg",true)
        Assert.assertEquals("DDRRULRDRD", solution!!.path)
    }

    @Test
    fun solveDay17Part2Example1() {
        val solution = solve("ihgpwlah", false)
        Assert.assertEquals(370, solution!!.path.length)
    }

    @Test
    fun solveDay17Part2Example2() {
        val solution = solve("kglvqrro",false)
        Assert.assertEquals(492, solution!!.path.length)
    }

    @Test
    fun solveDay17Part2Example3() {
        val solution = solve("ulqzkmiv",false)
        Assert.assertEquals(830, solution!!.path.length)
    }

    @Test
    fun solveDay17Part2() {
        val solution = solve("veumntbg",false)
        Assert.assertEquals(536, solution!!.path.length)
    }

}