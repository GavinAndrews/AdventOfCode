package aoc2016.day15

import org.junit.Assert
import org.junit.Test

class Day15Test {

    @Test
    fun solveDay15ExamplePart1() {

        val discs: List<Disc> = listOf(
                // Disc #1 has 5 positions; at time=0, it is at position 4.
                Disc(1, 5, 4),
                // Disc #2 has 2 positions; at time=0, it is at position 1.
                Disc(2, 2, 1)
        )

        val result = solve(discs, 0)
        Assert.assertEquals(5, result)
    }

    @Test
    fun solveDay15Part1() {

        val discs: List<Disc> = listOf(
                //    Disc #1 has 7 positions; at time =0, it is at position 0.
                Disc(1, 7, 0),
                //    Disc #2 has 13 positions; at time =0, it is at position 0.
                Disc(2, 13, 0),
                //    Disc #3 has 3 positions; at time =0, it is at position 2.
                Disc(3, 3, 2),
                //    Disc #4 has 5 positions; at time =0, it is at position 2.
                Disc(4, 5, 2),
                //    Disc #5 has 17 positions; at time =0, it is at position 0.
                Disc(5, 17, 0),
                //    Disc #6 has 19 positions; at time =0, it is at position 7.
                Disc(6, 19, 7)
        )

        val result = solve(discs, 0)
        Assert.assertEquals(121834, result)
    }

    @Test
    fun solveDay15Part2() {

        val discs: List<Disc> = listOf(
                //    Disc #1 has 7 positions; at time =0, it is at position 0.
                Disc(1, 7, 0),
                //    Disc #2 has 13 positions; at time =0, it is at position 0.
                Disc(2, 13, 0),
                //    Disc #3 has 3 positions; at time =0, it is at position 2.
                Disc(3, 3, 2),
                //    Disc #4 has 5 positions; at time =0, it is at position 2.
                Disc(4, 5, 2),
                //    Disc #5 has 17 positions; at time =0, it is at position 0.
                Disc(5, 17, 0),
                //    Disc #6 has 19 positions; at time =0, it is at position 7.
                Disc(6, 19, 7),
                // new disc with 11 positions and starting at position 0
                Disc(7,11,0)
        )

        val result = solve(discs, 0)
        Assert.assertEquals(3208099, result)
    }
}