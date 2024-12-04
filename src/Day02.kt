import kotlin.collections.windowed

typealias Report = List<Int>

private fun parse(input: List<String>): List<Report> =
    input.map { it.split(" ").map { it.toInt() } }

private fun isSafe(lists: Report): Boolean {
  val windows = lists.windowed(2)
  val safeDesc = windows.all { (a, b) -> (a - b) in 1..3 }
  val safeAsc = windows.all { (a, b) -> (b - a) in 1..3 }

  return safeAsc || safeDesc
}

private fun part1(input: List<Report>): Int = input.count(::isSafe)

private fun part2(input: List<Report>): Int =
    input.count { report ->
      report.indices.any {
        isSafe(report.toMutableList().apply { removeAt(it) })
      }
    }

fun main() {
  val testInput = parse(rawTestInput)
  val input = parse(readDayInput(2))

  // PART 1
  assertEquals(part1(testInput), 2)
  println("Part1: ${part1(input)}")

  // PART 2
  assertEquals(part2(testInput), 4)
  println("Part2: ${part2(input)}")
}

private val rawTestInput = """
    7 6 4 2 1
    1 2 7 8 9
    9 7 6 2 1
    1 3 2 4 5
    8 6 4 4 1
    1 3 6 7 9
"""
        .trimIndent()
        .lines()
