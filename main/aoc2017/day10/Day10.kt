package aoc2017.day10

import java.io.File

enum class Part {
    ONE, TWO
}

class HashString(private val n: Int) {

    var elements: MutableList<Int> = mutableListOf()

    private fun adjOffset(position: Int, offset: Int): Int {
        var adjOffset = position + offset
        while (adjOffset < 0) adjOffset += n
        while (adjOffset >= n) adjOffset -= n
        return adjOffset
    }

    private fun getElement(position: Int, offset: Int): Int {
        val adjOffset = adjOffset(position, offset)
        return elements[adjOffset]
    }

    private fun setElement(position: Int, offset: Int, value: Int) {
        val adjOffset = adjOffset(position, offset)
        elements[adjOffset] = value
    }

    fun reverseSegment(position: Int, length: Int) {

        val copy: MutableList<Int> = mutableListOf()
        (length - 1 downTo 0).forEach { i -> copy.add(getElement(position, i)) }
        for (i in 0 until length) {
            setElement(position, i, copy[i])
        }
    }


    fun calculateHashString(): String {
        var hash = ""

        for (i in 0..15) {
            var x = elements[i * 16]
            for (j in 1..15) {
                x = x xor elements[i * 16 + j]
            }
            x = x and 255

            var h = x.toString(16)
            if (h.length < 2) h = "0" + h
            hash += h
        }
        return hash
    }

    override fun toString(): String {
        return "HashString(elements=$elements)"
    }

    init {
        for (i in 0 until n) {
            elements.add(i)
        }
    }


}


fun main(args: Array<String>) {

    solveFile("main/aoc2017/day10/input.txt", 256, Part.TWO)
//    solve("main/aoc2017/day10/input.txt", 256)
}


fun solveFile(fname: String, n: Int, p: Part = Part.ONE): Any {
    val line = File(fname).readText()
    return solve(line, n, p)
}

fun solve(line: String, n: Int, p: Part = Part.ONE): Any {

    val rounds : Int
    val ops: List<Int>

    if (p == Part.ONE) {
        ops = line.split(",").map { s -> s.toInt() }
        rounds = 1
    } else {
        ops = line.map { c -> c.toInt() } + listOf(17, 31, 73, 47, 23)
        rounds = 64
    }

    val hs = HashString(n)

    var currentPosition = 0
    var currentSkip = 0

    for (round in 1..rounds) {
        for (op in ops) {
            hs.reverseSegment(currentPosition, op)
            currentPosition += op + currentSkip
            currentSkip++
        }
    }

    return if (p == Part.ONE) {
        hs.elements[0] * hs.elements[1]
    } else {
        val hash = hs.calculateHashString()
        hash
    }
}