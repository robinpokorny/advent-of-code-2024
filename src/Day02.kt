typealias Report = List<Int>

private fun parse(input: List<String>): List<Report> =
    input.map { it.split(" ").map { it.toInt() } }

private fun part1(input: List<Report>): Int =
    input
        .map { it.windowed(2) }
        .count {
          val safeDesc = it.all { (a, b) -> (a - b) in 1..3 }
          val safeAsc = it.all { (a, b) -> (b - a) in 1..3 }

          safeAsc || safeDesc
        }

private fun part2(input: List<Report>): Int {
  return 0
}

fun main() {
  val testInput = parse(rawTestInput)
  val input = parse(readDayInput(2))

  // PART 1
  assertEquals(part1(testInput), 2)
  println("Part1: ${part1(input)}")

  // PART 2
  assertEquals(part2(testInput), 0)
  println("Part2: ${part2(input)}")
}

private val rawTestInput =
    """
    7 6 4 2 1
    1 2 7 8 9
    9 7 6 2 1
    1 3 2 4 5
    8 6 4 4 1
    1 3 6 7 9
"""
        .trimIndent()
        .lines()
