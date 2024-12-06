import kotlin.collections.map

private fun parse(input: List<String>): List<Pair<Boolean, Int>> {
  val breakIndex = input.indexOf("")
  val rules =
      input.take(breakIndex).map { line ->
        line.split("|").map { it.toInt() }.let { it[0] to it[1] }
      }

  return input
      .drop(breakIndex + 1)
      .map { line -> line.split(",").map { it.toInt() } }
      .map {
        val fixed =
            it.sortedWith { a, b -> if (rules.contains(a to b)) -1 else 0 }

        (it == fixed) to fixed[fixed.size / 2]
      }
}

private fun part1(input: List<Pair<Boolean, Int>>): Int =
    input.filter { it.first }.sumOf { it.second }

private fun part2(input: List<Pair<Boolean, Int>>): Int =
    input.filter { !it.first }.sumOf { it.second }

fun main() {
  val testInput = parse(rawTestInput)
  val input = parse(readDayInput(5))

  // PART 1
  assertEquals(part1(testInput), 143)
  println("Part1: ${part1(input)}")

  // PART 2
  assertEquals(part2(testInput), 123)
  println("Part2: ${part2(input)}")
}

private val rawTestInput =
    """
    47|53
    97|13
    97|61
    97|47
    75|29
    61|13
    75|53
    29|13
    97|29
    53|29
    61|53
    97|53
    61|29
    47|13
    75|47
    97|75
    47|61
    75|61
    47|29
    75|13
    53|13

    75,47,61,53,29
    97,61,53,29,13
    75,29,13
    75,97,47,61,53
    61,13,29
    97,13,75,29,47
"""
        .trimIndent()
        .lines()
