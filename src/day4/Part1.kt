package day4

import java.io.File

enum class CellState {
    Empty, Occupied
}

class Matrix(val width: Int, val height: Int) {
    val cells = Array(width * height) { CellState.Empty }

    fun get(col: Int, row: Int): CellState =
        if (col !in 0..<width || row !in 0..<height) CellState.Empty else cells[row * width + col]

    fun get(coors: Pair<Int, Int>) = get(coors.first, coors.second)

    fun set(col: Int, row: Int, state: CellState) {
        if (col !in 0..<width || row !in 0..<height) return
        cells[row * width + col] = state
    }

    fun set(coors: Pair<Int, Int>, state: CellState) = set(coors.first, coors.second, state)

    fun readFromString(str: String) {
        for (i in str.indices) cells[i] = if (str[i] == '@') CellState.Occupied else CellState.Empty
    }

    fun getAccessible(): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()

        for (x in 0..<width) for (y in 0..<height) {
            if (get(x, y) == CellState.Empty) continue

            var neighbours = 0

            for (dx in -1..1) for (dy in -1..1) {
                if (dx == 0 && dy == 0) continue
                val neighbour = get(x + dx, y + dy)
                if (neighbour == CellState.Occupied) neighbours++
            }

            if (neighbours < 4) result += x to y
        }

        return result
    }
}

fun main() {
    val input = File("src/day4/data.txt").readLines()
    val matrix = Matrix(input[0].length, input.size)
    matrix.readFromString(input.joinToString(""))

    println(matrix.getAccessible().size)
}