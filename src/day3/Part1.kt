package day3

import java.io.File

fun main() {
    val input = File("src/day3/data.txt").readLines().map { it.map { d -> d.digitToInt() } }
    var result = 0UL

    for (row in input) {
        val l = row.size - 1

        var rowResult = 0UL
        var prev = -1

        for (offset in 11 downTo 0) {
            val start = prev + 1
            val end = l - offset

            val found = row.maxIndex(start, end)
            rowResult *= 10UL
            rowResult += row[found].toUInt()

            prev = found
        }

        println(rowResult)
        result += rowResult
    }

    println(result)
}