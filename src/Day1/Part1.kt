package Day1

import java.io.File

enum class Direction {
    LEFT, RIGHT
}

data class Instruction(val dir: Direction, val shift: Int)

fun parseInstruction(line: String): Instruction {
    val dir = when (line[0]) {
        'L' -> Direction.LEFT
        'R' -> Direction.RIGHT
        else -> throw IllegalArgumentException("Invalid direction: $line")
    }

    return Instruction(dir, line.substring(1).toInt())
}

infix fun Int.eumod(divisor: Int) = ((this % divisor) + divisor) % divisor

fun main() {
    val input = File("src/Day1/data.txt").readLines()
    val instructions = input.map(::parseInstruction)

    var currentPos = 50
    var zeroCounter = 0

    for (i in instructions) {
        currentPos += i.shift * (if (i.dir == Direction.LEFT) -1 else 1)
        currentPos = currentPos eumod 100
        if (currentPos == 0) zeroCounter++
    }

    println(zeroCounter)
}