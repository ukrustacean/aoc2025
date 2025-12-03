package day3

import java.io.File

fun main() {
    val input = File("src/day3/data.txt").readLines().map { it.map { d -> d.digitToInt() } }
    var result = 0

    for (row in input) {
        val l = row.size - 1

        var max1 = 0
        for (i in 1..<l) {
            if (row[i] > row[max1]) max1 = i
        }

        var max2 = max1 + 1
        for (i in (max1 + 2)..l) {
            if (row[i] > row[max2]) max2 = i
        }

        result += row[max1] * 10 + row[max2]
    }

    println(result)
}