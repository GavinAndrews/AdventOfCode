package aoc2016.day02

import java.io.File

data class KeyPadPosition(val label:String) {
    var up : KeyPadPosition? = null
    var down : KeyPadPosition? = null
    var left : KeyPadPosition? = null
    var right : KeyPadPosition? = null

    fun setNeighbours(up:KeyPadPosition?, right:KeyPadPosition?, down:KeyPadPosition?, left:KeyPadPosition?) {
        this.up = up
        this.down = down
        this.left = left
        this.right = right
    }
}

fun scenario1(fname:String) : String {

    val key1 = KeyPadPosition("1")
    val key2 = KeyPadPosition("2")
    val key3 = KeyPadPosition("3")
    val key4 = KeyPadPosition("4")
    val key5 = KeyPadPosition("5")
    val key6 = KeyPadPosition("6")
    val key7 = KeyPadPosition("7")
    val key8 = KeyPadPosition("8")
    val key9 = KeyPadPosition("9")

    key1.setNeighbours(null, key2, key4, null)
    key2.setNeighbours(null, key3, key5, key1)
    key3.setNeighbours(null, null, key6, key5)
    key4.setNeighbours(key1, key5, key7, null)
    key5.setNeighbours(key2, key6, key8, key4)
    key6.setNeighbours(key3, null, key9, key5)
    key7.setNeighbours(key4, key8, null, null)
    key8.setNeighbours(key5, key9, null, key7)
    key9.setNeighbours(key6, null, null, key8)

    return traverse(fname, key5)
}

private fun traverse(fname: String, initialPosition: KeyPadPosition): String {

    var result = ""
    val text = File(fname).readLines()

    var current = initialPosition

    for (line in text) {

        for (c in line) {
            when (c) {
                'L' -> current = current.left ?: current
                'R' -> current = current.right ?: current
                'U' -> current = current.up ?: current
                'D' -> current = current.down ?: current
            }
        }
        result += current.label
    }

    return result
}

fun scenario2(fname:String) :String {

    val key2 = KeyPadPosition("2")
    val key3 = KeyPadPosition("3")
    val key4 = KeyPadPosition("4")
    val key6 = KeyPadPosition("6")
    val key7 = KeyPadPosition("7")
    val key8 = KeyPadPosition("8")
    val keyA = KeyPadPosition("A")
    val keyB = KeyPadPosition("B")
    val keyC = KeyPadPosition("C")

    val key1 = KeyPadPosition("1")
    val key5 = KeyPadPosition("5")
    val key9 = KeyPadPosition("9")
    val keyD = KeyPadPosition("D")

    key2.setNeighbours(null, key3, key6, null)
    key3.setNeighbours(key1, key4, key7, key2)
    key4.setNeighbours(null, null, key8, key7)
    key6.setNeighbours(key2, key7, keyA, key5)
    key7.setNeighbours(key3, key8, keyB, key6)
    key8.setNeighbours(key4, key9, keyC, key7)
    keyA.setNeighbours(key6, keyB, null, null)
    keyB.setNeighbours(key7, keyC, keyD, keyA)
    keyC.setNeighbours(key8, null, null, keyB)

    key1.setNeighbours(null, null, key3, null)
    key5.setNeighbours(null, key6, null, null)
    key9.setNeighbours(null, null, null, key8)
    keyD.setNeighbours(keyB, null, null, null)

    return traverse(fname, key5)
}