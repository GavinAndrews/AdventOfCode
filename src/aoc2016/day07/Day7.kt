package aoc2016.day07

import java.io.File

fun part1(fname: String) {

    var count = 0
    var count2 = 0

    val lines = File(fname).readLines()


    for (l in lines) {

        val abaSuperhet = mutableListOf<String>()
        val abaHypernet = mutableListOf<String>()

        val parts = l.split("[", "]")

        var abbaFound = false
        var hyperAbbaFound = false
        var isHyper = false

        for (p in parts) {

            if (!isHyper) {
                if (extractABBAs(p).isNotEmpty()) {
                    abbaFound = true
                }
                abaSuperhet.addAll(extractABAs(p))
            } else {
                if (extractABBAs(p).isNotEmpty()) {
                    hyperAbbaFound = true
                }
                abaHypernet.addAll(extractABAs(p))
            }

            isHyper = !isHyper
        }

        if (abbaFound && !hyperAbbaFound) {
            print("GOOD ")
            count++
        } else {
            print("BAD  ")
        }

        for (sh in abaSuperhet) {
            val equiv = sh.substring(1,2) + sh.substring(0,1)+sh.substring(1,2)
            if (abaHypernet.contains(equiv)) {
                count2++
                break
            }
        }

        println(l)

    }

    println(count)
    println(count2)
}

fun extractABBAs(s: String): List<String> {
    val result: MutableList<String> = mutableListOf()

    (0 until s.length - 3)
            .filter { (s[it] == s[it + 3]) && (s[it + 1] == s[it + 2]) && (s[it] != s[it + 1]) }
            .mapTo(result) { s.substring(it, it + 4) }

    return result
}

fun extractABAs(s: String): List<String> {
    val result: MutableList<String> = mutableListOf()

    (0 until s.length - 2)
            .filter { (s[it] == s[it + 2]) && (s[it] != s[it + 1]) }
            .mapTo(result) { s.substring(it, it + 3) }

    return result
}
