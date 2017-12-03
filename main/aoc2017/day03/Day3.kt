package aoc2017.day03

data class Position(val x:Int, val y:Int) {
    fun next(d:Direction) : Position {
        when (d) {
            Direction.UP -> return Position(x,y+1)
            Direction.DOWN -> return Position(x,y-1)
            Direction.LEFT -> return Position(x-1,y)
            else -> return Position(x+1,y)
        }
    }

    fun distance() : Int {
        return Math.abs(x)+Math.abs(y)
    }
}

enum class Direction { RIGHT, UP, LEFT, DOWN }

fun nextDirection(d:Direction):Direction {
    when (d) {
        Direction.UP -> return Direction.LEFT
        Direction.LEFT -> return Direction.DOWN
        Direction.DOWN -> return Direction.RIGHT
        else -> return Direction.UP
    }
}

val memory : MutableMap<Position, Int> = mutableMapOf()
val accumulators : MutableMap<Position, Int> = mutableMapOf()
val inverse: MutableMap<Int, Position> = mutableMapOf()


fun main(args: Array<String>) {

    val upperLimit = 312051

    var current = Position(0,0)
    var value = 1
    var firstBigAccumulator:Int? = null

    memory[current] = value
    accumulators[current] = value
    inverse[value] = current

    var direction = Direction.DOWN

    for (i in 1..upperLimit) {
        // Turn Left if empty
        val left = nextDirection(direction)
        val next = current.next(left)

        if (!memory.containsKey(next)) {
            direction = left
        }

        current = current.next(direction)
        value++
        memory[current] = value

        val calculateSum = calculateSum(current)
        if ((calculateSum> upperLimit)&&(firstBigAccumulator==null)) {
            firstBigAccumulator = calculateSum
        }
        accumulators[current] = calculateSum
        inverse[value] = current
    }

    println("${inverse[1]} ${inverse[1]!!.distance()}")
    println("${inverse[12]} ${inverse[12]!!.distance()}")
    println("${inverse[23]} ${inverse[23]!!.distance()}")
    println("${inverse[1024]} ${inverse[1024]!!.distance()}")
    println("${inverse[312051]} ${inverse[312051]!!.distance()}")

    println(firstBigAccumulator)
    println("Done")
}

fun calculateSum(current: Position): Int {

    var total = 0

    for (dx in -1..1) {
        for (dy in -1..1) {
            val other = accumulators[Position(current.x+dx, current.y+dy)]
            if (other != null) total += other
        }
    }

    return total
}
