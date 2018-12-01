package day04

import java.io.File

//aaaaa-bbb-z-y-x-123[abxyz] is a real room because the most common letters are a (5), b (3), and then a tie between x, y, and z, which are listed alphabetically.
//a-b-c-d-e-f-g-h-987[abcde] is a real room because although the letters are all tied (1 of each), the first five are listed alphabetically.
//not-a-real-room-404[oarel] is a real room.
//totally-real-room-200[decoy] is not.

fun main(args: Array<String>) {

    var total = 0

//    val lines = File("src/day04/example.txt").readLines()
    val lines = File("main/aoc2016/day04/input.txt").readLines()
    for (l in lines) {


        val r = Regex("""(.*)\-(\d+)\[(.*)\]""")

        val g : MatchResult? = r.matchEntire(l)

        if (g != null) {
            val rawLetters = g.groupValues[1]

            val letters = rawLetters.replace("-", "")
            val sector = g.groupValues[2]
            val checksum = g.groupValues[3]

            val counters : MutableMap<Char, Int> = mutableMapOf()

            for (l in letters) {
                if (!counters.contains(l)) {
                    counters.put(l, 0)
                }
                var current = counters.get(l)
                val current1 = current!! +1
                counters.put(l, current1)
            }

            val sorted = counters.entries.toList().sortedWith(compareByDescending<MutableMap.MutableEntry<Char, Int>> { it.value }.thenBy({ it.key }))

            val calculatedChecksum = sorted.fold(""){sum, element -> sum + element.key}.substring(0, 5)

            if (calculatedChecksum==checksum) {
                total += sector.toInt()

                val decrypted = decrypt(rawLetters, sector.toInt() )

                if (decrypted.startsWith("northpole"))
                println("$decrypted $sector")
            }

        }
    }
    println(total)

}

fun decrypt(rawLetters: String, i: Int): String {

    var s = ""
    for (c in rawLetters) {
        if (c.equals('-')) {
            s+=c
        } else
        {
            var offset = c.toInt()-'a'.toInt()
            offset += i
            offset %= 26
            offset += 'a'.toInt()
            val newChar = offset.toChar()
            s += newChar
        }
    }

    return s

}
