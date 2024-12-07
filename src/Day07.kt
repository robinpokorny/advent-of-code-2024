private fun parse(input: List<String>): List<Pair<Long, List<Long>>> =
    input.map {
      val (first, second) = it.split(": ")
      val equation = second.split(" ").map { it.toLong() }

      first.toLong() to equation
    }

private fun solve(
    result: Long,
    equation: List<Long>,
    acc: Long = 0,
    index: Int = 0
): Boolean {
  if (index == equation.size) return result == acc
  if (acc > result) return false

  val next = equation[index]

  return solve(result, equation, acc + next, index + 1) ||
      solve(result, equation, acc * next, index + 1)
}

private fun part1(input: List<Pair<Long, List<Long>>>): Long =
    input
        .filter { (result, equation) -> solve(result, equation) }
        .fold(0L) { acc, pair -> acc + pair.first }

private fun part2(input: List<Pair<Long, List<Long>>>): Int {
  return 0
}

fun main() {
  val testInput = parse(rawTestInput)
  val input = parse(readDayInput(7))

  // PART 1
  assertEquals(part1(testInput), 3749L)
  println("Part1: ${part1(input)}")

  // PART 2
  assertEquals(part2(testInput), 0)
  println("Part2: ${part2(input)}")
}

private val rawTestInput =
    """
    190: 10 19
    3267: 81 40 27
    83: 17 5
    156: 15 6
    7290: 6 8 6 15
    161011: 16 10 13
    192: 17 8 14
    21037: 9 7 18 13
    292: 11 6 16 20
"""
        .trimIndent()
        .lines()
