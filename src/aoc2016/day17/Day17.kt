package aoc2016.day17

import java.security.MessageDigest


val md = MessageDigest.getInstance("MD5")!!

data class DoorStates(val upOpen: Boolean, val downOpen: Boolean, val leftOpen: Boolean, val rightOpen: Boolean)

fun evalDoors(stem: String, path: String): DoorStates {
    val hash = makeHash(stem, path)
    return DoorStates(isDoorOpen(hash[0]), isDoorOpen(hash[1]), isDoorOpen(hash[2]), isDoorOpen(hash[3]))

}

fun isDoorOpen(c: Char): Boolean {
    return ("bcdef".contains(c))
}

private fun makeHash(stem: String, path: String): String {
    val digestBytes = md.digest((stem + path).toByteArray())
    val digest = asHex(digestBytes)
    return digest.toLowerCase()
}

private fun asHex(bytes: ByteArray): String {
    var s = ""
    bytes
            .map { String.format("%02X", it) }
            .forEach { s += it }
    return s
}


data class Position(val x: Int, val y: Int, val path: String) {

    fun positionsFromHere(stem: String, path: String): List<Position> {
        val possibles: MutableList<Position> = mutableListOf()

        val (up, down, left, right) = evalDoors(stem, path)

        if (y > 0) {
            if (up) possibles.add(Position(x, y - 1, path + "U"))
        }
        if (y < 3) {
            if (down) possibles.add(Position(x, y + 1, path + "D"))
        }
        if (x > 0) {
            if (left) possibles.add(Position(x - 1, y, path + "L"))
        }
        if (x < 3) {
            if (right) possibles.add(Position(x + 1, y, path + "R"))
        }
        return possibles
    }


}

val initial = Position(0, 0, "")
val queue = mutableListOf<Position>()
val visited: MutableSet<Position> = mutableSetOf()

fun traverse(seed: String, stopOnVault: Boolean): Position? {

    var solutionPosition:Position? = null

    queue.clear()
    visited.clear()

    queue.add(initial)
    visited.add(initial)

    while (queue.size > 0) {
        val position = queue.removeAt(0)

//        print(position)
//        print(makeHash(seed, position.path))
//        println(position.positionsFromHere(seed, position.path))

        if ((position.x == 3) && (position.y == 3)) {
            if (stopOnVault) {
                return position
            } else {
                if ((solutionPosition==null)||(solutionPosition.path.length<position.path.length)) {
                    solutionPosition=position
                }
            }
        } else {
            val positionsFromHere = position.positionsFromHere(seed, position.path)
            for (p in positionsFromHere) {
                if (!visited.contains(p)) {
                    visited.add(p)
                    queue.add(p)
                }
            }
        }
    }
    return solutionPosition
}


fun main(args: Array<String>) {
    val seed = "ihgpwlah"
    println("Seed: $seed")
    val position = traverse(seed, false)
    println(position)
    println(position!!.path.length)
}

fun solve(seed: String, stopOnVault:Boolean): Position? {
    return traverse(seed, stopOnVault)
}