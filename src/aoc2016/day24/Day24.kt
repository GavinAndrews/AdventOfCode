package aoc2016.day24

import aoc2016.math.Permutations
import java.io.File
import kotlin.coroutines.experimental.buildSequence


class Map(private val nRows: Int, private val nCols: Int, private val lines: List<String>) {

    lateinit var mapPoints: Array<Array<MapSpot>>

    fun generate() {

        fun buildMapSpot(row: Int, col: Int, code: String): MapSpot {
            return MapSpot(row, col, code)
        }

        mapPoints = Array(nRows, { row ->
            Array(nCols, { col ->
                buildMapSpot(row, col, getMapCode(row, col))
            })
        })
    }

    private fun getMapCode(row: Int, col: Int): String {
        return lines[row].substring(col, col + 1)
    }

    fun dump2() {
        for (i in 0 until nRows) {
            for (j in 0 until nCols) {

                val distanceToOrigin = mapPoints[i][j].distanceToOrigin
                print(if ((distanceToOrigin >= 0) && (distanceToOrigin < 36)) "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(distanceToOrigin, distanceToOrigin + 1) else "~")
            }
            println()
        }
    }

    val getCheckPoints = buildSequence {
        for (i in 0 until nRows) {
            for (j in 0 until nCols) {
                val mp = mapPoints[i][j]
                if (mp.isCheckpoint) yield(mp)
            }
        }
    }

    fun reset() {
        for (i in 0 until nRows) {
            for (j in 0 until nCols) {
                val mp = mapPoints[i][j]
                mp.reset()
            }
        }
    }

    fun traverse(cp: MapSpot) {

        val queue: MutableList<Pair<MapSpot, Int>> = mutableListOf()

        queue.add(Pair(cp, 0))

        while (!queue.isEmpty()) {

            val p = queue.removeAt(0)
            val mp = p.first
            val d = p.second

            val row = mp.row
            val col = mp.col

            //println("Traverse $row, $col")

            mp.distanceToOrigin = d

            var otherMapSpot: MapSpot

            otherMapSpot = mapPoints[row - 1][col]
            if (otherMapSpot.isNavigable && otherMapSpot.distanceToOrigin == -1) {
                otherMapSpot.distanceToOrigin = 0
                queue.add(Pair(otherMapSpot, d + 1))
            }

            otherMapSpot = mapPoints[row + 1][col]
            if (otherMapSpot.isNavigable && otherMapSpot.distanceToOrigin == -1) {
                otherMapSpot.distanceToOrigin = 0
                queue.add(Pair(otherMapSpot, d + 1))
            }

            otherMapSpot = mapPoints[row][col - 1]
            if (otherMapSpot.isNavigable && otherMapSpot.distanceToOrigin == -1) {
                otherMapSpot.distanceToOrigin = 0
                queue.add(Pair(otherMapSpot, d + 1))
            }

            otherMapSpot = mapPoints[row][col + 1]
            if (otherMapSpot.isNavigable && otherMapSpot.distanceToOrigin == -1) {
                otherMapSpot.distanceToOrigin = 0
                queue.add(Pair(otherMapSpot, d + 1))
            }
        }
    }

}


class MapSpot(val row: Int, val col: Int, private val code: String) {
    var isNavigable: Boolean
    var isCheckpoint: Boolean
    var checkPointNumber = 0
    var distanceToOrigin = -1

    init {
        when (code) {
            "#" -> {
                isNavigable = false
                isCheckpoint = false
            }
            "." -> {
                isNavigable = true
                isCheckpoint = false
            }
            else -> {
                isNavigable = true
                isCheckpoint = true
                checkPointNumber = code.toInt()
            }
        }
    }

    override fun toString(): String {
        return "MapSpot(row=$row, col=$col, code='$code', isNavigable=$isNavigable, isCheckpoint=$isCheckpoint, checkPointNumber=$checkPointNumber, distanceToOrigin=$distanceToOrigin)"
    }

    fun reset() {
        distanceToOrigin = -1
    }
}

fun loadMap(returnToStart:Boolean) : Int {

    val lines = File("main/aoc2016/day24/input.txt").readLines()

    var nCols = 0

    for (line in lines) {
        println(line)
        if (line.length > nCols) nCols = line.length
    }

    val nRows = lines.size
    println("Map: Rows: $nRows, Cols: $nCols")
    val map = Map(nRows, nCols, lines)

    map.generate()

    // Extra all checkpoints....
    val checkpoints = map.getCheckPoints.sortedBy { mapSpot -> mapSpot.checkPointNumber }
    for (cp in checkpoints) {
        println(cp)
    }

    val routings: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()

    for (cp in checkpoints) {
        map.reset()
        map.traverse(cp)
        map.dump2()

        // Determine Distances to other checkpoints
        for (cp2 in checkpoints) {
            val distanceToOrigin = map.mapPoints[cp2.row][cp2.col].distanceToOrigin
            routings.put(Pair(cp.checkPointNumber, cp2.checkPointNumber), distanceToOrigin)
        }
    }

    for (routing in routings) {
        print(routing.key)
        print(routing.value)
        print(",")
    }

    var bestDistance: Int? = null
    var bestConfiguration: List<Int>? = null

    for (configuration in Permutations(checkpoints.count() - 1)) {

        var current = 0
        var distance = 0

        print("0")

        val adjConfiguration = configuration.map { i -> i + 1 }

        for (cpNumber in adjConfiguration) {
            print(",")
            print(cpNumber)

            val currentRoute = Pair(current, cpNumber)
            distance += routings[currentRoute]!!
            current = cpNumber
        }

        // OPTIONAL.. Navigate back to 0
        if (returnToStart) {
            distance += routings[Pair(current, 0)]!!
            print("0")
        }

        print(", d=")
        println(distance)

        if (bestDistance == null || distance < bestDistance) {
            bestDistance = distance
            bestConfiguration = adjConfiguration
        }
    }

    print("Best Distance: ")
    print(bestDistance)
    print(", Best Configuration: 0->")
    print(bestConfiguration)
    if (returnToStart) print("->0")
    println()

    return bestDistance!!
}