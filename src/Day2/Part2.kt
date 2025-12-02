package Day2

import java.io.File

fun main() {
    val input = File("src/Day2/data.txt").readText()
    val regex = Regex("""(\d+)-(\d+)""")

    var result = 0L

    for (match in regex.findAll(input)) {
        val range = LongRange(
            match.groupValues[1].toLong(),
            match.groupValues[2].toLong())

        for (i in range) {
            val s = i.toString()

            val eagPat = Regex("""(\d+)\1+""")
            val lazPat = Regex("""(\d+?)\1+""")

            if (eagPat.matches(s) || lazPat.matches(s)) {
                result += s.toLong()
            }
        }
    }

    println(result)
}