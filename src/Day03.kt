private fun parse(input: List<String>) =
    Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)")
        .findAll(input.joinToString(""))
        .map { it.groups[1]!!.value.toInt() to it.groups[2]!!.value.toInt() }
        .toList()

private fun part1(input: List<Pair<Int, Int>>): Int =
    input.sumBy { (a, b) -> a * b }

private fun part2(input: List<String>): Int {
  return 0
}

fun main() {
  val testInput = parse(rawTestInput)
  val input = parse(readDayInput(3))

  // PART 1
  assertEquals(part1(testInput), 161)
  println("Part1: ${part1(input)}")

  // PART 2
  // assertEquals(part2(testInput), 0)
  // println("Part2: ${part2(input)}")
}

private val rawTestInput =
    """
    xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
"""
        .trimIndent()
        .lines()
