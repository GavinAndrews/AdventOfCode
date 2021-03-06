package aoc2016.day25

import java.io.File

var memory: MutableList<Instruction> = mutableListOf()
var registers:MutableMap<String,Int> = mutableMapOf()
var pc=0

class RegOrLiteralRef(symbol : String) {

    val isLiteral:Boolean
    val symbol:String

    init {
        this.symbol = symbol
        isLiteral = symbol.matches(Regex("""-*\d+"""))
    }

    override fun toString(): String {
        return "RegOrLiteralRef(isLiteral=$isLiteral, symbol='$symbol')"
    }

    fun  read(): Int {
        if (isLiteral) {
            return symbol.toInt()
        } else
        {
            if (!registers.contains(symbol)) registers.put(symbol, 0)
            return registers[symbol]!!
        }
    }

    fun write(newValue : Int) {
        registers.put(symbol, newValue)
    }
}

abstract class Instruction {
    abstract val opcode:String
    abstract val operand1 : RegOrLiteralRef
    abstract val operand2 : RegOrLiteralRef?
    abstract fun execute()
}

class CPY(parts: List<String>) : Instruction() {
    override val opcode = "cpy"

    override val operand1 : RegOrLiteralRef = RegOrLiteralRef(parts[1])
    override val operand2 : RegOrLiteralRef? = RegOrLiteralRef(parts[2])

    override fun toString(): String {
        return "CPY(opcode='$opcode', operand1=$operand1, operand2=$operand2)"
    }

    override fun execute() {
        val arg = operand1.read()
        operand2!!.write(arg)
        pc++
    }
}

class JNZ(parts: List<String>) : Instruction() {
    override val opcode = "jnz"

    override val operand1 : RegOrLiteralRef = RegOrLiteralRef(parts[1])
    override val operand2 : RegOrLiteralRef? = RegOrLiteralRef(parts[2])

    override fun toString(): String {
        return "JNZ(opcode='$opcode', operand1=$operand1, operand2=$operand2)"
    }
    override fun execute() {
        val arg = operand1.read()
        val rel = operand2!!.read()
        if (arg==0) {
            pc++
        } else
        {
            pc += rel
        }
    }
}

class INC(parts: List<String>) : Instruction() {
    override val opcode = "inc"

    override val operand1 : RegOrLiteralRef = RegOrLiteralRef(parts[1])
    override val operand2 : RegOrLiteralRef? = null

    override fun toString(): String {
        return "INC(opcode='$opcode', operand1=$operand1"
    }
    override fun execute() {
        var arg = operand1.read()
        arg++
        operand1.write(arg)
        pc++
    }
}

class OUT(parts: List<String>) : Instruction() {
    override val opcode = "out"

    override val operand1 : RegOrLiteralRef = RegOrLiteralRef(parts[1])
    override val operand2 : RegOrLiteralRef? = null

    override fun toString(): String {
        return "OUT(opcode='$opcode', operand1=$operand1"
    }
    override fun execute() {
        var arg = operand1.read()
        print(arg.toChar())
        pc++
    }
}

class DEC(parts: List<String>) : Instruction() {
    override val opcode = "dec"

    override val operand1 : RegOrLiteralRef = RegOrLiteralRef(parts[1])
    override val operand2 : RegOrLiteralRef? = null

    override fun toString(): String {
        return "DEC(opcode='$opcode', operand1=$operand1"
    }
    override fun execute() {
        var arg = operand1.read()
        arg--
        operand1.write(arg)
        pc++
    }
}


fun main(args: Array<String>) {
    println("Day25")

//    val text = File("src/Day25/input.txt").readLines()
    val text = File("main/aoc2016/day25/bonus.txt").readLines()
    for (s in text) {
        parseOp(s)
    }

    //registers.put("a", 175)

    // Memory is Loaded...
    while (pc< memory.size) {
        val op = memory[pc]
//        print("Execute: PC($pc) $op")
        op.execute()
//        println(registers)
    }

    println("Final State: $registers")

}


fun parseOp(s:String) {
    // Split into Parts
    val parts = s.split(" ")

    var op : Instruction? = null

    val opcode = parts[0]
    when (opcode) {
        "cpy" -> op = CPY(parts)
        "inc" -> op = INC(parts)
        "dec" -> op = DEC(parts)
        "jnz" -> op = JNZ(parts)
        "out" -> op = OUT(parts)
        else -> println("FAIL!!!!!!! $s")
    }

//    println(op)

    if (op != null) {
        memory.add(op)
    }
}
