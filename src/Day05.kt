typealias Rule = Pair<Int, Int>

typealias Update = List<Int>

private fun parse(input: List<String>): Pair<List<Rule>, List<Update>> {
  val breakIndex = input.indexOf("")
  val rules =
      input.take(breakIndex).map { line ->
        line.split("|").map { it.toInt() }.let { it[0] to it[1] }
      }

  val updates =
      input.drop(breakIndex + 1).map { line ->
        line.split(",").map { it.toInt() }
      }

  return rules to updates
}

private fun part1(input: Pair<List<Rule>, List<Update>>): Int {
  val (rules, updates) = input

  val before = rules.groupBy({ it.first }, { it.second })

  return updates
      .filter { update ->
        update.withIndex().all { (i, value) ->
          update.take(i).intersect(before[value] ?: emptyList()).isEmpty()
        }
      }
      .sumOf { it[it.size / 2] }
}

private fun part2(input: Pair<List<Rule>, List<Update>>): Int {
  return 0
}

fun main() {
  val testInput = parse(rawTestInput)
  val input = parse(readDayInput(5))

  // PART 1
  assertEquals(part1(testInput), 143)
  println("Part1: ${part1(input)}")

  // PART 2
  assertEquals(part2(testInput), 0)
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
