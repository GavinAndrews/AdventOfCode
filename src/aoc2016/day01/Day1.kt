package aoc2016.day01

class Position(var x: Int, var y: Int, val direction: Int = 0) {
// 0 = North, 1=East, 2=South, 3=West

    fun apply(i: Instruction, withTrails: Boolean = false): List<Position> {

        var newDirection = direction

        // First change direction
        when (i.direction) {
            "L" -> newDirection--
            else -> newDirection++
        }

        // Normalise
        newDirection = if (newDirection < 0) newDirection + 4 else newDirection
        newDirection = if (newDirection > 3) newDirection - 4 else newDirection

        var newX = x
        var newY = y

        val stepValue = if (withTrails) 1 else i.distance

        val positions: MutableList<Position> = mutableListOf()

        for (d in i.distance downTo 1 step stepValue) {
            when (newDirection) {
                0 -> newY += stepValue
                2 -> newY -= stepValue
                1 -> newX += stepValue
                3 -> newX -= stepValue
            }
            positions.add(Position(newX, newY, newDirection))
        }
        return positions
    }

    fun distanceToHome() = Math.abs(x) + Math.abs(y)

    override fun toString(): String {
        return "Position(x=$x, y=$y, direction=$direction)"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Position) return false
        val otherPosition: Position = other
        return x == otherPosition.x && y == otherPosition.y
    }

    override fun hashCode(): Int {
        return x + y * 1001
    }
}

class Instruction(s: String) {

    val direction: String = s.substring(0, 1)
    val distance: Int = s.substring(1).toInt()

    override fun toString(): String {
        return "Direction: " + this.direction + ", Distance: " + distance
    }
}

fun processInstructionSequence(instructions: String, quitOnMatch: Boolean = false): Int {

    val positionsVisited = mutableSetOf<Position>()

    println(instructions)
    var currentPosition = Position(0, 0)
    println(currentPosition)

    positionsVisited.add(currentPosition)

    val parts = instructions.replace(" ", "").split(",")
    for (part in parts) {
        val instr = Instruction(part)
        println(instr)

        for (newPosition in currentPosition.apply(instr, true)) {

            currentPosition = newPosition

            println(currentPosition)
            if (quitOnMatch && positionsVisited.contains(currentPosition)) {
                println("Match" + currentPosition.toString())
                return currentPosition.distanceToHome()
            } else {
                positionsVisited.add(currentPosition)
            }
        }
    }

    val distanceToHome = currentPosition.distanceToHome()
    println(distanceToHome)

    return distanceToHome
}