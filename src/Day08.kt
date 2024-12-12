private typealias AntennaMap = Map<Char, List<Point>>

private fun parse(input: List<String>): Pair<Int, AntennaMap> =
    input.size to
        input
            .flatMapIndexed { y, line ->
              line.mapIndexedNotNull { x, c ->
                if (c != '.') c to Point(x, y) else null
              }
            }
            .groupBy({ it.first }, { it.second })

private fun part1(input: Pair<Int, AntennaMap>): Int {
  val (size, antennaMap) = input

  return antennaMap.values
      .flatMap<List<Point>, Point> { antennas ->
        val pairs =
            antennas.flatMapIndexed { i, a ->
              antennas.drop(i).mapNotNull { b -> if (a != b) a to b else null }
            }

        pairs.flatMap { (a, b) ->
          val vector = a - b
          if (a.y > b.y) listOf(a - vector, b + vector)
          else listOf(a + vector, b - vector)
        }
      }
      .toSet()
      .filter { point -> point.x in 0 until size && point.y in 0 until size }
      .size
}

private fun part2(input: Pair<Int, AntennaMap>): Int {
  return 0
}

fun main() {
  val testInput = parse(rawTestInput)
  val input = parse(readDayInput(8))

  // PART 1
  assertEquals(part1(testInput), 14)
  println("Part1: ${part1(input)}")

  // PART 2
  assertEquals(part2(testInput), 0)
  println("Part2: ${part2(input)}")
}

private val rawTestInput =
    """
    ............
    ........0...
    .....0......
    .......0....
    ....0.......
    ......A.....
    ............
    ............
    ........A...
    .........A..
    ............
    ............
"""
        .trimIndent()
        .lines()
