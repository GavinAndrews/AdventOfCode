package aoc2016.day16

//val initial = "10000"
//val target = 20

val initial = "10010000000110000"
//val target = 272
val target = 35651584


fun main(args: Array<String>) {

    var stuff = initial

    while (stuff.length<target) {
        stuff = grow(stuff)
        println("Length: ${stuff.length}")
    }

    // Trim to target
    stuff = stuff.substring(0, target)

    // println(stuff)
    println(checksum(stuff))
}

fun grow(stuff: String): String {
    var copy = ""

    for (c in stuff.length-1 downTo 0) {
        copy += if (stuff[c]=='0') "1" else "0"
    }

    return stuff+"0"+copy
}

fun checksum(stuff: String) : String {
    if (stuff.length % 2 != 0) return stuff

    var result = ""
    for (i in 0..stuff.length-1 step 2) {
        val c1 = stuff[i]
        val c2 = stuff[i+1]
        result += if (c1==c2) "1" else "0"
    }
    return checksum(result)
}