package aoc2015.day05

import java.io.File

val regExDoubles = Regex(""".*(.)\1.*""")

fun part1NicenessTest(s: String): Boolean {
    val vowels = (s.count { c -> c == 'a' } + s.count { c -> c == 'e' } + s.count { c -> c == 'i' } + s.count { c -> c == 'o' } + s.count { c -> c == 'u' }) >= 3
    val forbidden = s.contains("ab") || s.contains("cd") || s.contains("pq") || s.contains("xy")
    val doubles = regExDoubles.matches(s)
    return vowels && !forbidden && doubles
}

val regExRepeatedDoubles = Regex(""".*(..).*\1.*""")
val regExXYX = Regex(""".*(.).\1.*""")

fun part2NicenessTest(s: String): Boolean {
    val repeatedDoubles = regExRepeatedDoubles.matches(s)
    val xYx = regExXYX.matches(s)
    return repeatedDoubles && xYx
}

fun solveFile(fname: String, nicenessTest: (String) -> Boolean = ::part1NicenessTest): Int {
    val lines = File(fname).readLines()
    return lines.count { solve(it, nicenessTest) }
}

fun solve(input: String, nicenessTest: (String) -> Boolean = ::part1NicenessTest): Boolean {
    return nicenessTest.invoke(input)
}