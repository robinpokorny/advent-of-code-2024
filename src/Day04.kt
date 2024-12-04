typealias World = Map<Point, Char>

private fun parse(input: List<String>): World =
    input
        .flatMapIndexed() { y, line ->
          line.replace(Regex("[^XMAS]"), ".").mapIndexed { x, c ->
            Point(x, y) to c
          }
        }
        .toMap()

fun to3Direction(point: Point): List<Point> =
    (1..3).scan(Point(0, 0)) { acc, _ -> acc + point }

val directions = Point.AROUND.map(::to3Direction)

private fun part1(input: World): Int =
    input
        .filterValues { it == 'X' }
        .keys
        .sumOf { point ->
          directions.count { dir ->
            dir.mapNotNull { input[point + it] }.joinToString("") == "XMAS"
          }
        }

private fun part2(input: World): Int {
  return 0
}

fun main() {
  val testInput = parse(rawTestInput)
  val input = parse(readDayInput(4))

  // PART 1
  assertEquals(part1(testInput), 18)
  println("Part1: ${part1(input)}")

  // PART 2
  assertEquals(part2(testInput), 0)
  println("Part2: ${part2(input)}")
}

private val rawTestInput =
    """
    MMMSXXMASM
    MSAMXMSMSA
    AMXSXMAAMM
    MSAMASMSMX
    XMASAMXAMM
    XXAMMXXAMA
    SMSMSASXSS
    SAXAMASAAA
    MAMMMXMMMM
    MXMXAXMASX
"""
        .trimIndent()
        .lines()
