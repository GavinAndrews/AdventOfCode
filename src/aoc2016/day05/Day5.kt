package aoc2016.day05

import java.security.MessageDigest


fun asHex(bytes: ByteArray): String {
    var s: String = ""
    for (b in bytes) {
        val st = String.format("%02X", b)
        s += st
    }
    return s
}


fun part1(stem:String) : String {

    var result = ""

    var i = 0L
    var found = 0

    while (found < 8) {
        val code = checkHash(stem, i)
        if (code != null) {
            print(code.first)
            result += code.first
            found++
        }
        i++
    }
    println()
    println("Done")
    return result
}


fun part2(stem: String) : String {

    var result = ""

    var i = 0L
    var found = 0

    val solution: MutableList<String?> = mutableListOf(null, null, null, null, null, null, null, null)
    val regex = Regex("""[0-7]""")

    while (found<8) {
        val code = checkHash(stem, i)

        if (code != null) {
            val first = code.first
            val second = code.second
            if (first.matches(regex)) {
                val f = first.toInt()
                if (solution[f] == null) {
                    solution[f]=second
                    found++
                    for (s in solution) {
                        if (s==null) {
                            print("-")
                        } else
                        {
                            print(s.toLowerCase())
                        }
                    }
                    println()
                }
            }
        }
        i++
    }

    for (s in solution) {
        if (s!==null) {
            result += s
        }
    }
    return result
}

private fun checkHash(stem: String, i: Long): Pair<String, String>? {
    val md = MessageDigest.getInstance("MD5")
    val digestBytes = md.digest((stem + i.toString()).toByteArray())
    val digest = asHex(digestBytes)

    if (digest.startsWith("00000")) {
        return Pair(digest.substring(5, 6), digest.substring(6, 7))
    } else {
        return null
    }
}