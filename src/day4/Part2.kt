package day4

import java.io.File

fun main() {
    val input = File("src/day4/data.txt").readLines()
    val matrix = Matrix(input[0].length, input.size)
    matrix.readFromString(input.joinToString(""))

    var result = 0
    var accessible: List<Pair<Int, Int>>

    do {
        accessible = matrix.getAccessible()
        result += accessible.size
        for (coors in accessible) matrix.set(coors, CellState.Empty)
    } while (accessible.isNotEmpty())

    println(result)
}