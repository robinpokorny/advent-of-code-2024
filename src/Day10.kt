import kotlin.collections.component1

private fun parse(input: List<String>) =
    input
        .flatMapIndexed() { y, line ->
          line.mapIndexed { x, c -> Point(x, y) to c.digitToInt() }
        }
        .toMap()

private fun findTrail(
    topoMap: Map<Point, Int>,
    current: Point,
    visited: MutableSet<Point> = mutableSetOf<Point>()
): Int {
  if (current in visited) return 0
  visited.add(current)
  if (topoMap[current] == 9) return 1

  return Point.ADJANCENT.map { current + it }
      .filter { it in topoMap && topoMap[it] == topoMap[current]!! + 1 }
      .sumOf { findTrail(topoMap, it, visited) }
}

private fun findDistinctTrails(topoMap: Map<Point, Int>, current: Point): Int {
  if (topoMap[current] == 9) return 1

  return Point.ADJANCENT.map { current + it }
      .filter { it in topoMap && topoMap[it] == topoMap[current]!! + 1 }
      .sumOf { findDistinctTrails(topoMap, it) }
}

private fun part1(topoMap: Map<Point, Int>): Int =
    topoMap
        .filter { (_, value) -> value == 0 }
        .keys
        .sumOf { findTrail(topoMap, it) }

private fun part2(topoMap: Map<Point, Int>): Int =
    topoMap
        .filter { (_, value) -> value == 0 }
        .keys
        .sumOf { findDistinctTrails(topoMap, it) }

fun main() {
  val testInput = parse(rawTestInput)
  val input = parse(readDayInput(10))

  // PART 1
  assertEquals(part1(testInput), 36)
  println("Part1: ${part1(input)}")

  // PART 2
  assertEquals(part2(testInput), 81)
  println("Part2: ${part2(input)}")
}

private val rawTestInput =
    """
    89010123
    78121874
    87430965
    96549874
    45678903
    32019012
    01329801
    10456732
"""
        .trimIndent()
        .lines()
