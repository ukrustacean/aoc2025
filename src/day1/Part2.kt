package day1

import java.io.File

fun main() {
    val input = File("src/day1/data.txt").readLines()
    var instructions = input.map(::parseInstruction)

    var currentPos = 50
    var zeroCounter = 0

    instructions = instructions.map {
        zeroCounter += it.shift / 100
        it.copy(shift = it.shift % 100)
    }

    for (i in instructions) {
        val shift = i.shift * (if (i.dir == Direction.LEFT) -1 else 1)
        currentPos += shift

        if ((shift != currentPos) && (currentPos < 0 || currentPos > 100)) zeroCounter++
        currentPos = currentPos eumod 100

        if (currentPos == 0) zeroCounter++
    }

    println(zeroCounter)
}