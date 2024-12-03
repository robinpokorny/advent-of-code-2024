import kotlin.math.abs

private fun parse(input: List<String>): Pair<List<Int>, List<Int>> =
    input
        .map {
          it.split("\\s+".toRegex()).let { it[0].toInt() to it[1].toInt() }
        }
        .unzip()

private fun part1(input: Pair<List<Int>, List<Int>>): Int =
    input.first.sorted().zip(input.second.sorted()).sumOf { (x, y) ->
      abs(x - y)
    }

private fun part2(input: Pair<List<Int>, List<Int>>): Int {
  val (left, right) = input

  val occurrences = right.groupingBy { it }.eachCount()

  return left.sumOf { it * (occurrences[it] ?: 0) }
}

fun main() {
  val testInput = parse(rawTestInput)
  val input = parse(readDayInput(1))

  // PART 1
  assertEquals(part1(testInput), 11)
  println("Part1: ${part1(input)}")

  // PART 2
  assertEquals(part2(testInput), 31)
  println("Part2: ${part2(input)}")
}

private val rawTestInput =
    """
    3   4
    4   3
    2   5
    1   3
    3   9
    3   3
"""
        .trimIndent()
        .lines()
