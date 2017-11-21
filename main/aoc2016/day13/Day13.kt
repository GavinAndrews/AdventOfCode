package aoc2016.day13

fun func(x: Int, y: Int): Int {
    return x * x + 3 * x + 2 * x * y + y + y * y + favorite
}

fun popCount(i: Int) = java.lang.Integer.bitCount(i)

fun bitsEven(x: Int): Boolean {
    val b = popCount(x) % 2 == 0
    return b
}

fun isOpen(x: Int, y: Int): Boolean {
    return bitsEven(func(x, y))
}

val favorite = 1362

data class Position(val x: Int, val y: Int) {
    fun neighbours(): List<Position> {
        val neighbours = mutableListOf<Position>()
        if (x > 0) {
            neighbours.add(Position(x - 1, y))
        }
        if (y > 0) {
            neighbours.add(Position(x, y - 1))
        }
        neighbours.add(Position(x + 1, y))
        neighbours.add(Position(x, y + 1))
        return neighbours
    }

    fun navigableNeighbours(): List<Position> {
        val neighbours = neighbours()
        return neighbours.filter { (x, y) -> isOpen(x, y) }
    }
}


data class State(val position: Position, val path: List<Position>)

fun traverse2(initialX: Int, initialY: Int, targetX: Int, targetY: Int): State? {

    val queue = mutableListOf<State>()
    val visited: MutableSet<Position> = mutableSetOf()
    val initial = State(Position(initialX, initialY), listOf())

    queue.add(initial)
    visited.add(initial.position)

    while (queue.size > 0) {
        val current = queue.removeAt(0)
        val currentPosition = current.position
        val path = current.path

        if ((currentPosition.x == targetX) && (currentPosition.y == targetY)) {
            return current
        }

        for (p in currentPosition.navigableNeighbours()) {
            if (!visited.contains(p)) {
                visited.add(p)
                queue.add(State(p, path + p))
            }
        }
    }
    return null
}

fun main(args: Array<String>) {
    val state = traverse2(1, 1, 31, 39)
    println("Distance ${state!!.path.size}")

    var reachable = 0

    (0..51).forEach { targetX ->
        (0..51).filter { targetY -> isOpen(targetX, targetY) }
                .map { targetY -> traverse2(1, 1, targetX, targetY) }
                .filter { it != null && it.path.size <= 50 }
                .forEach { reachable++ }
    }

    println("Reachable in 50: $reachable")
}