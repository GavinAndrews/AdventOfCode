package aoc2016.day10

import java.io.File
import kotlin.streams.toList

abstract class MoveRule {
    abstract fun execute(i: Int)
}

class Output(private val id:Int) {
    var value:Int? = null
    fun append(i: Int) {
        println("MOVE $i TO OUTPUT $id")
        value=i;
    }
}

val bots: MutableMap<Int, Bot> = mutableMapOf()
val outputs: MutableMap<Int, Output> = mutableMapOf()

class BotMoveRule(private val target: Int) : MoveRule() {
    override fun execute(i: Int) {
        println("MOVE $i TO BOT $target")
        val bot = bots[target]
        bot?.addValue(i)
    }
}

class OutputMoveRule(private val target: Int) : MoveRule() {
    override fun execute(i: Int) {
        if (!outputs.containsKey(target)) {
            outputs.put(target, Output(target))
        }
        val output = outputs[target]
        output?.append(i)
    }
}

data class Bot(private val id: Int) {

    val values: MutableList<Int> = mutableListOf()

    private var lowMoveRule: MoveRule? = null
    private var highMoveRule: MoveRule? = null

    fun addValue(b: Int) {
        values.add(b)

        // If we have two values then fire rules...
        processRules()
    }

    fun processRules() : Boolean {
        if ((values.size == 2) && (lowMoveRule != null) && (highMoveRule != null)) {
            val sortedValues = values.stream().sorted().toList()
            values.clear()

            if ((sortedValues[0]==17)&&(sortedValues[1]==61)) {
                println("******************* BOT $id")
            }

            lowMoveRule?.execute(sortedValues[0])
            highMoveRule?.execute(sortedValues[1])
            return true
        }
        return false
    }

    override fun toString(): String {
        return "Bot(id=$id, values=$values)"
    }

    fun setLowRule(rule: MoveRule) {
        lowMoveRule = rule
    }

    fun setHighRule(rule: MoveRule) {
        highMoveRule = rule
    }
}

fun main(args: Array<String>) {

    val fname = "main/aoc2016/day10/input.txt"

    val lines = File(fname).readLines()

    for (line in lines) {

        if (line.startsWith("value")) {
            // value 5 goes to bot 2

            val regex = Regex("""value (\d+) goes to bot (\d+)""")

            val matches = regex.matchEntire(line)

            val value = matches!!.groups[1]!!.value.toInt()
            val targetBot = matches.groups[2]!!.value.toInt()


            println("VALUE $value -> BOT $targetBot")

            if (!bots.contains(targetBot)) {
                bots.put(targetBot, Bot(targetBot))
            }

            val bot = bots[targetBot]
            bot!!.addValue(value)

        } else {

            val regex = Regex("""bot (\d+) gives low to (\w+) (\d+) and high to (\w+) (\d+)""")

            val matches = regex.matchEntire(line)

            val a = matches!!.groups[1]!!.value.toInt()

            // Ensure it exists
            if (!bots.contains(a)) {
                bots.put(a, Bot(a))
            }

            val bot = bots[a]

            val b = matches.groups[2]!!.value
            val b2 = matches.groups[3]!!.value.toInt()

            var lowRule: MoveRule?
            lowRule = if (b == "bot") {
                BotMoveRule(b2)
            } else {
                OutputMoveRule(b2)
            }

            bot!!.setLowRule(lowRule)

            val c = matches.groups[4]!!.value
            val c2 = matches.groups[5]!!.value.toInt()

            var highRule: MoveRule?
            highRule = if (c == "bot") {
                BotMoveRule(c2)
            } else {
                OutputMoveRule(c2)
            }

            bot.setHighRule(highRule)

            println("MOVE RULE: FROM $a LOW [ $b $b2 ], HIGH [ $c $c2 ]")
        }
    }

    var changed = true
    while (changed) {
        changed=false
        for (bot in bots.values) {
            if (bot.processRules()) changed=true
        }
    }
    println("DONE")

    val o0 = outputs[0]?.value ?: 0
    val o1 = outputs[1]?.value  ?: 0
    val o2 = outputs[2]?.value ?: 0

    println("Output0 : $o0")
    println("Output1 : $o1")
    println("Output2 : $o2")
    println("Product ${o0*o1*o2}")
}