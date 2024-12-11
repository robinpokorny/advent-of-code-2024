import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

private fun parse(input: List<String>) =
    input.first().split(' ').map(String::toLong)

private fun blink(
    cache: MutableMap<Pair<Int, Long>, Long>,
    remains: Int,
    stone: Long
): Long {
  if (remains == 0)
      return 1L

  return cache.getOrPut(remains to stone) {
    if (stone == 0L) return@getOrPut blink(cache, remains - 1, 1L)

    val log = floor(log10(stone.toDouble())) + 1
    if (log % 2 == 0.0) {
      return@getOrPut blink(cache, remains - 1, stone / 10.0.pow(log / 2).toLong()) +
          blink(cache, remains - 1, stone % 10.0.pow(log / 2).toLong())
    }

    blink(cache, remains - 1, stone * 2024)
  }
}

private fun part1(input: List<Long>): Long {
    val cache = mutableMapOf<Pair<Int, Long>, Long>()
  return input.sumOf { blink(cache, 25, it) }
}

private fun part2(input: List<Long>): Long {
    val cache = mutableMapOf<Pair<Int, Long>, Long>()
    return input.sumOf { blink(cache, 75, it) }
}

fun main() {
  val testInput = parse(rawTestInput)
  val input = parse(readDayInput(11))

  // PART 1
  assertEquals(part1(testInput), 55312L)
  println("Part1: ${part1(input)}")

  // PART 2
  assertEquals(part2(testInput), 65601038650482L)
  println("Part2: ${part2(input)}")
}

private val rawTestInput =
    """
    125 17
"""
        .trimIndent()
        .lines()
