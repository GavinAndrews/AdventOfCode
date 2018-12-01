package aoc2016.day15

data class Disc(val id:Int, val size:Int, val initial:Int) {
    fun getPosition(t:Int) : Int {
        return (initial+t)%size
    }
}

fun isPassable(discs: List<Disc>, t:Int): Boolean {
    val impassableDiscs = discs.filter{d -> d.getPosition(t+d.id)!=0}
    return impassableDiscs.isEmpty()
}

fun solve(discs:List<Disc>, t0 : Int) : Int {
    var t = t0
    while (!isPassable(discs, t)) t++
    return t
}

