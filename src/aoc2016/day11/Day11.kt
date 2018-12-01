package aoc2016.day11

//F4 .  .  .  .  .
//F3 .  .  .  LG .
//F2 .  HG .  .  .
//F1 E  .  HM .  LM

abstract class Item {
    abstract var id: Int
}

data class Chip(override var id: Int) : Item() {
    override fun toString(): String {
        return "C$id"
    }
}

data class Generator(override var id: Int) : Item() {
    override fun toString(): String {
        return "G$id"
    }
}

data class Floor(val floorNumber: Int, val contents: List<Item>) {
    fun isSafe(): Boolean {

        val generators = contents.filter { it is Generator }
        val chips = contents.filter { it is Chip }

        val unMatchedGenerators = generators.filter { item -> !chips.contains(Chip(item.id)) }
        val unMatchedChips = chips.filter { item -> !generators.contains(Generator(item.id)) }

        return unMatchedChips.isEmpty() || generators.isEmpty()
    }
}

data class Building(val floors: List<Floor>, val elevatorFloor: Floor) {
    fun render() {
        println("Building----------------------------")
        for (f in floors.asReversed()) {
            if (f.isSafe()) print(" ") else print("!")
            print(f.floorNumber)
            if (elevatorFloor === f) print("E") else print(" ")
            for (item in f.contents) {
                print(" $item")
            }
            println()
        }
        println("------------------------------------")
    }

    fun simplify(): List<List<Int>> {

        val ll: MutableList<List<Int>> = mutableListOf(listOf(elevatorFloor.floorNumber))

        val map: MutableMap<Item, Floor> = mutableMapOf()
        for (f in floors) {
            for (i in f.contents) {
                map.put(i, f)
            }
        }

        for (f in floors.reversed()) {
            val l: MutableList<Int> = mutableListOf()
            for (i in f.contents) {
                if (i is Chip) {
                    val f2 = map.get(Generator(i.id))
                    l.add(f2!!.floorNumber - f.floorNumber)
                } else {
                    val f2 = map.get(Chip(i.id))
                    if (f2 == null) {
                        println("Trouble at mill")
                    }
                    val delta = f2!!.floorNumber - f.floorNumber
                    if (delta != 0) l.add(delta)
                }
            }
            ll.add(l.sorted())
        }

        return ll
    }

    fun itemPairs(f: Floor): Set<Pair<Item, Item?>> {
        val items = f.contents
        val pairs: MutableSet<Pair<Item, Item?>> = mutableSetOf()

        for (i1: Int in 0 until items.size) {
            for (i2: Int in i1 until items.size) {
                if (i1 == i2) {
                    pairs.add(Pair(items[i1], null))
                } else {
                    pairs.add(Pair(items[i1], items[i2]))
                }
            }
        }
        return pairs
    }

    fun applyPair(nextFloor: Floor, pair: Pair<Item, Item?>): Building {
        var list = elevatorFloor.contents.toMutableList().minus(pair.first)
        if (pair.second != null) {
            val second: Item = pair.second!!
            list = list.minus(second)
        }


        val adjElevatorFloor = Floor(elevatorFloor.floorNumber, list)

        var list2 = nextFloor.contents.toMutableList().plus(pair.first)
        if (pair.second != null) {
            val second: Item = pair.second!!
            list2 = list2.plus(second)
        }

        val adjNextFloor = Floor(nextFloor.floorNumber, list2)

        val newFloors = mutableListOf<Floor>()
        for (f in floors) {
            if (f.floorNumber == elevatorFloor.floorNumber) {
                newFloors.add(adjElevatorFloor)
            } else if (f.floorNumber == nextFloor.floorNumber) {
                newFloors.add(adjNextFloor)
            } else {
                newFloors.add(f)
            }
        }
        val newBuilding = Building(newFloors, adjNextFloor)
        return newBuilding
    }

    fun isSafe(): Boolean {
        val firstUnsafe = floors.find { floor -> !floor.isSafe() }
        return firstUnsafe == null
    }

    override fun equals(other: Any?): Boolean {
        if (other is Building) {
            return simplify().equals(other.simplify())
        } else {
            return false
        }
    }

    override fun hashCode(): Int {
        var h = 0
        val s = simplify()
        for (l in s) {
            for (i in l) {
                h += i
            }
            h *= 7
        }
        return h
    }
}

data class State(val building: Building, val depth: Int, val path: List<Pair<Item, Item?>>)

fun main(args: Array<String>) {

//    val ff4 = Floor(3, listOf(Chip(1), Chip(2), Generator(1), Generator(2)))
//    val ff3 = Floor(2, listOf())
//    val ff2 = Floor(1, listOf())
//    val ff1 = Floor(0, listOf())
//    val bf = Building(listOf(ff1, ff2, ff3, ff4), ff4)
//
//    val f4 = Floor(3, listOf())
//    val f3 = Floor(2, listOf(Generator(2)))
//    val f2 = Floor(1, listOf(Generator(1)))
//    val f1 = Floor(0, listOf(Chip(1), Chip(2)))
//    val b = Building(listOf(f1, f2, f3, f4), f1)


//    The first floor contains a thulium generator, a thulium-compatible microchip, a plutonium generator, and a strontium generator.
//    The second floor contains a plutonium-compatible microchip and a strontium-compatible microchip.
//    The third floor contains a promethium generator, a promethium-compatible microchip, a ruthenium generator, and a ruthenium-compatible microchip.
//    The fourth floor contains nothing relevant.

//    val ff4 = Floor(3, listOf(Chip(1), Chip(2), Chip(3), Chip(4), Chip(5), Generator(1), Generator(2), Generator(3), Generator(4), Generator(5)))
//    val ff3 = Floor(2, listOf())
//    val ff2 = Floor(1, listOf())
//    val ff1 = Floor(0, listOf())
//    val bf = Building(listOf(ff1, ff2, ff3, ff4), ff4)
//
//    val f4 = Floor(3, listOf())
//    val f3 = Floor(2, listOf(Chip(4),Generator(4),Chip(5),Generator(5)))
//    val f2 = Floor(1, listOf(Chip(2),Chip(3)))
//    val f1 = Floor(0, listOf(Chip(1), Generator(1),Generator(2),Generator(3)))
//    val b = Building(listOf(f1, f2, f3, f4), f1)


    val (b, bf) = buildScenario()

    val initialState = State(b, 0, listOf())

    val queue = mutableListOf<State>()
    val visited: MutableSet<Building> = mutableSetOf()

    queue.add(initialState)
    visited.add(initialState.building)

    while (queue.size > 0) {
        val current = queue.removeAt(0)

        val b = current.building

        val pairs = b.itemPairs(b.elevatorFloor)

        // We can go up or down... UP FIRST
        val upFloorNumber = b.elevatorFloor.floorNumber + 1
        if (upFloorNumber < current.building.floors.size) {
            val nextFloor = b.floors[upFloorNumber]
            processMove(pairs, b, nextFloor, visited, current, bf, queue)
        }
        // Now DOWN...
        val downFloorNumber = b.elevatorFloor.floorNumber - 1
        if (downFloorNumber>=0) {
            val nextFloor = b.floors[downFloorNumber]
            processMove(pairs, b, nextFloor, visited, current, bf, queue)
        }
    }
}

private fun buildScenario(): Pair<Building, Building> {
    val ff4 = Floor(3, listOf(Chip(1), Chip(2), Chip(3), Chip(4), Chip(5), Chip(6), Chip(7), Generator(1), Generator(2), Generator(3), Generator(4), Generator(5), Generator(6), Generator(7)))
    val ff3 = Floor(2, listOf())
    val ff2 = Floor(1, listOf())
    val ff1 = Floor(0, listOf())
    val bf = Building(listOf(ff1, ff2, ff3, ff4), ff4)

    val f4 = Floor(3, listOf())
    val f3 = Floor(2, listOf(Chip(4), Generator(4), Chip(5), Generator(5)))
    val f2 = Floor(1, listOf(Chip(2), Chip(3)))
    val f1 = Floor(0, listOf(Chip(1), Generator(1), Generator(2), Generator(3), Chip(6), Generator(6), Chip(7), Generator(7)))
    val b = Building(listOf(f1, f2, f3, f4), f1)
    return Pair(b, bf)
}

private fun processMove(pairs: Set<Pair<Item, Item?>>, b: Building, nextFloor: Floor, visited: MutableSet<Building>, current: State, bf: Building, queue: MutableList<State>) {
    for (p in pairs) {
        val b2 = b.applyPair(nextFloor, p)

        if (!visited.contains(b2)) {
            if (b2.isSafe()) {
                val newState = State(b2, current.depth + 1, current.path.toMutableList().plus(p))
                if (b2 == bf) {
                    println("SOLUTION!!!! Depth = ${newState.depth}")
                    println(newState)
                } else {
                    queue.add(newState)
                    visited.add(b2)
                }
            }
        }
    }
}