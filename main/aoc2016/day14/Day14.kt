package aoc2016.day14

import java.security.MessageDigest


private fun asHex(bytes: ByteArray): String {
    var s = ""
    bytes
            .map { String.format("%02X", it) }
            .forEach { s += it }
    return s
}

val md = MessageDigest.getInstance("MD5")!!

private fun checkHash(stem: String, i: Long): String {
    val digestBytes = md.digest((stem + i.toString()).toByteArray())
    val digest = asHex(digestBytes)
    return digest.toLowerCase()
}

fun stretch(s: String): String {

    var s2: String = s

    for (i in 1..2016) {
        val digestBytes = md.digest(s2.toByteArray())
        val digest = asHex(digestBytes).toLowerCase()
        s2 = digest
    }
    return s2
}

var currentSeed = 0L
val hashCache: MutableList<String> = mutableListOf()

fun reset() {
    currentSeed = 0L
    hashCache.clear()
}

fun getNextHash(salt: String, enableStretch: Boolean): Triple<Long, String, List<String>> {
    while (hashCache.size < 1001) {
        val hash = if (enableStretch) stretch(checkHash(salt, currentSeed)) else checkHash(salt, currentSeed)
        hashCache.add(hash)
        currentSeed++
    }
    val front = hashCache.removeAt(0)
    return Triple(currentSeed - 1001, front, hashCache)
}

fun extractTriples(s: String): List<Char> {

    val result: MutableList<Char> = mutableListOf()

    (0..s.length - 3)
            .filter { (s[it] == s[it + 1]) && (s[it + 1] == s[it + 2]) }
            .mapTo(result) { s[it] }
    return result
}

fun extractQuintuples(s: String): List<Char> {
    val result: MutableList<Char> = mutableListOf()
    (0..s.length - 5)
            .filter { (s[it] == s[it + 1]) && (s[it + 1] == s[it + 2]) && (s[it + 2] == s[it + 3]) && (s[it + 3] == s[it + 4]) }
            .mapTo(result) { s[it] }
    return result.distinct()
}

fun main(args: Array<String>) {
//    solve("abc")
    val result = solve("zpqevtbw", false)
    println(result)
}

fun solve(salt: String, enableStretch: Boolean = false): Long? {

    reset()

    var lastSeed: Long? = null

    var keysFound = 0

    while (keysFound < 64) {
        val (seed, head, tail) = getNextHash(salt, enableStretch)
        val triples = extractTriples(head)
        if (triples.isNotEmpty()) {
            val quintuples = tail.flatMap { s -> extractQuintuples(s) }.distinct()
            val intersect = quintuples.contains(triples.first())
            if (intersect) {
                keysFound++
                lastSeed = seed
                println("$keysFound $seed $head ... ${tail[0]} ${tail[1]} ${tail[2]} ... ")
            }
        }
    }

    return lastSeed
}