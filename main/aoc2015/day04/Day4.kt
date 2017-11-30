package aoc2015.day04

import java.security.MessageDigest

fun asHex(bytes: ByteArray): String {
    var s = ""
    bytes
            .map { String.format("%02X", it).toLowerCase() }
            .forEach { s += it }
    return s
}

private fun makeHash(stem: String, i: Long): String {
    val md = MessageDigest.getInstance("MD5")
    val digestBytes = md.digest((stem + i.toString()).toByteArray())
    return asHex(digestBytes)
}

fun solve(stem:String, start:String="00000") : Long {

    var seed = 0L

    while (true) {
        val hash = makeHash(stem, seed)
        if (hash.startsWith(start)) break
        seed++
    }

    return seed
}