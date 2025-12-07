package day5

import java.io.File

class RangeTree(var range: LongRange) {
    var left: RangeTree? = null
    var right: RangeTree? = null

    fun insert(item: LongRange) {
        if (item.isEmpty()) return
        if (item.first in range && item.last in range) return

        if (item.first in range && item.last !in range) {
            insert((range.last + 1)..item.last)
            return
        }

        if (item.first !in range && item.last in range) {
            insert(item.first..(range.first - 1))
            return
        }

        if (item.first < range.first && range.last < item.last) {
            insert(item.first..(range.first - 1))
            insert((range.last + 1)..item.last)
            return
        }

        if (item.last < range.first) if (left == null) left = RangeTree(item) else left?.insert(item)
        if (range.last < item.first) if (right == null) right = RangeTree(item) else right?.insert(item)
    }

    operator fun contains(item: Long): Boolean {
        if (item in range) return true
        if (item < range.first) return left?.contains(item) ?: false
        if (item > range.last) return right?.contains(item) ?: false
        return false
    }

    fun print() {
        left?.print()
        println(range)
        right?.print()
    }

    fun count(): Long = (left?.count() ?: 0L) - range.first + 1 + range.last + (right?.count() ?: 0L)
}

fun main() {
    val input = File("src/day5/data.txt").readText()
    val rangePat = Regex("""^(\d+)-(\d+)$""", RegexOption.MULTILINE)
    val ingrPat = Regex("""^(\d+)$""", RegexOption.MULTILINE)

    var result = 0L

    val ranges = rangePat.findAll(input)
    val firstRange = ranges.first()
    val tree = RangeTree(firstRange.groupValues[1].toLong()..firstRange.groupValues[2].toLong())

    for (match in ranges.drop(1)) {
        tree.insert(match.groupValues[1].toLong()..match.groupValues[2].toLong())
    }

    for (match in ingrPat.findAll(input)) {
        if (match.groupValues[1].toLong() in tree) result++
    }

    println(result)
}