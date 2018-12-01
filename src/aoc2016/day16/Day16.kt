package aoc2016.day16

import java.util.*

fun growSet(bits: BitSet, hwm: Int): Int {
    bits.clear(hwm)
    val newHwm = hwm * 2 + 1
    for (i in 0 until hwm) {
        val b = bits.get(i)
        if (b) {
            bits.clear((newHwm - 1) - i)
        } else {
            bits.set((newHwm - 1) - i)
        }
    }
    return newHwm
}

fun fromString(bitset: BitSet, s: String): Int {
    s.forEachIndexed { index, c ->
        run {
            if (c == '1') bitset.set(index) else bitset.clear(index)
        }
    }
    return s.length
}

fun solve(initial:String, target:Int) : String {

    val bits = BitSet()
    var hwm = fromString(bits, initial)

    while (hwm < target) {
        hwm = growSet(bits, hwm)
    }

    // Truncate to target
    hwm = target

    do {
        hwm = squeeze(bits, hwm)
    } while (hwm % 2 == 0)

    return dumpSet(bits, hwm)
}

fun squeeze(bits: BitSet, hwm: Int): Int {
    // Odd indicates cannot be squeezed
    if (hwm % 2 != 0) return hwm
    (0..hwm - 2 step 2).forEach { i ->
        if (bits.get(i) == bits.get(i + 1)) {
            bits.set(i / 2)
        } else {
            bits.clear(i / 2)
        }
    }
    return hwm / 2
}

fun dumpSet(bits: BitSet, hwm: Int) : String {
    var s = ""
    for (i in 0 until hwm) {
        s += if (bits.get(i)) "1" else "0"
    }
    return s
}