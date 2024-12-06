data class World(
    val size: Point,
    val guard: Pair<Point, Point>,
    val obstacles: Set<Point>
)

private fun parse(input: List<String>): World {
  val size = Point(input.first().length, input.size)

  val obstacles =
      input
          .flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, c ->
              if (c == '#') Point(x, y) else null
            }
          }
          .toSet()

  val guard =
      input
          .flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, c ->
              if (c == '^') Point(x, y) else null
            }
          }
          .first() to Point.UP

  return World(size, guard, obstacles)
}

fun isInside(world: World, point: Point): Boolean {
  return point.x in 0 until world.size.x && point.y in 0 until world.size.y
}

private fun part1(world: World): Int {
  var currentGuard = world.guard
  val visited = mutableSetOf<Point>()

  while (isInside(world, currentGuard.first)) {
    visited.add(currentGuard.first)

    val next = currentGuard.first - currentGuard.second
    if (next !in world.obstacles) {
      currentGuard = next to currentGuard.second
    } else {
      val nextDirection =
          when (currentGuard.second) {
            Point.UP -> Point.LEFT
            Point.LEFT -> Point.DOWN
            Point.DOWN -> Point.RIGHT
            Point.RIGHT -> Point.UP
            else -> error("Invalid direction")
          }
      currentGuard = currentGuard.first to nextDirection
    }
  }

  return visited.size
}

private fun part2(world: World): Int {
  return 0
}

fun main() {
  val testInput = parse(rawTestInput)
  val input = parse(readDayInput(6))

  // PART 1
  assertEquals(part1(testInput), 41)
  println("Part1: ${part1(input)}")

  // PART 2
  assertEquals(part2(testInput), 0)
  println("Part2: ${part2(input)}")
}

private val rawTestInput =
    """
    ....#.....
    .........#
    ..........
    ..#.......
    .......#..
    ..........
    .#..^.....
    ........#.
    #.........
    ......#...
"""
        .trimIndent()
        .lines()
