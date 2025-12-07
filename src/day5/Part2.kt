package day5

import java.io.File

fun main() {
    val input = File("src/day5/data.txt").readText()
    val rangePat = Regex("""^(\d+)-(\d+)$""", RegexOption.MULTILINE)

    val ranges = rangePat.findAll(input)
    val firstRange = ranges.first()
    val tree = RangeTree(firstRange.groupValues[1].toLong()..firstRange.groupValues[2].toLong())

    for (match in ranges.drop(1)) {
        tree.insert(match.groupValues[1].toLong()..match.groupValues[2].toLong())
    }

    println(tree.count())
}
