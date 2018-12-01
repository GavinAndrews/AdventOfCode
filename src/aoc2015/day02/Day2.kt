package aoc2015.day02

import java.io.File

val regex: Regex = Regex("""(\d+)x(\d+)x(\d+)""")

data class Prism(val l:Int, val w:Int, val h:Int) {

    fun smallestArea() : Int {
        val sides = listOf(l,w,h).sorted()
        return sides[0]*sides[1]
    }

    fun paperRequired() : Int {
        return 2*l*w + 2*w*h + 2*h*l + smallestArea()
    }

    fun ribbonRequired(): Int {
        val sides = listOf(l,w,h).sorted()
        return 2*sides[0]+2*sides[1]+l*w*h
    }

}

fun makePrism(s:String) : Prism {
    val matchResult = regex.matchEntire(s)
    return Prism(matchResult!!.groups[1]!!.value.toInt(), matchResult.groups[2]!!.value.toInt(), matchResult.groups[3]!!.value.toInt())
}

fun  solveFile(fname: String) : Pair<Int, Int>{

    val lines = File(fname).readLines()

    var totalPaper = 0
    var totalRibbon = 0

    for (line in lines) {
        val prism = makePrism(line)
        totalPaper += prism.paperRequired()
        totalRibbon += prism.ribbonRequired()
    }

    return Pair(totalPaper, totalRibbon)
}
