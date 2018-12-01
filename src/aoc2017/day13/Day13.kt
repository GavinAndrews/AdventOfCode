package aoc2017.day13

import java.io.File


class FireWall {
    constructor(layers: List<Layer>) {
        this.layers = layers
    }

    val layers: List<Layer>

    var packetLayer: Int? = null

    fun advance(): Boolean {
        packetLayer = packetLayer?.plus(1)
        return (packetLayer!! < layers.size)
    }

    fun tick() {


        layers.forEach { l -> l.tick() }

        // Move Packet

    }

    fun collision(): Boolean {
        if ((packetLayer == null)||(packetLayer==-1)) return false
        return layers[packetLayer!!].current == 0
    }

    fun collisionCost(): Int {
        return layers[packetLayer!!].depth * layers[packetLayer!!].range
    }
}

class Layer {

    constructor(depth: Int, range: Int?) {
        this.depth = depth
        this.range = if (range != null) range else 0
    }

    val depth: Int
    val range: Int

    var current: Int = 0
    var moveDown: Boolean = true

    fun tick() {
        if (range != 0) {
            if (moveDown) {
                current++
                if (current == (range - 1)) moveDown = false
            } else {
                // Move Up
                current--
                if (current == 0) moveDown = true
            }

        }
    }
}

fun solve(layers: List<Layer>, initialDelay: Int): Pair<Int,Int> {

    var delay = initialDelay

    val fw = FireWall(layers)

    fw.packetLayer = -1

    var severity = 0
    var collisions = 0

    while (true) {

        if (delay == 0) {
            val inPlay = fw.advance()
            if (!inPlay) break
        } else {
            delay--
        }

        if (fw.collision()) {
            println("Collision in layer ${fw.packetLayer}")
            collisions++
            severity += fw.collisionCost()
        }

        fw.tick()
    }

    println(collisions)
    println(severity)
    return Pair(severity,collisions)
}

enum class Part {
    ONE, TWO
}

fun solveFile(fname: String, part: Part = Part.ONE): Int {
    val lines = File(fname).readLines()

    val layerMap: MutableMap<Int, Int> = mutableMapOf()

    for (line in lines) {
        val parts = line.split(": ").map { s -> s.toInt() }
        val layerNum = parts[0]
        val layerRange = parts[1]
        layerMap[layerNum] = layerRange
    }

    val layerMax = layerMap.keys.max() as Int

    val layers: MutableList<Layer> = mutableListOf()

    for (layer in 0..layerMax) {
        layers += Layer(layer, layerMap[layer])
    }

    var delay = 0

    println("DELAY 0-----------------------------")
    while (solve(layers, delay).second > 0) {
        delay++
        println("DELAY $delay-----------------------------")
    }

    print(delay)

    return 0

}

fun main(args: Array<String>) {
//    solveFile("main/aoc2017/day13/input.txt", Part.ONE)
    solveFile("main/aoc2017/day13/example.txt", Part.ONE)
}