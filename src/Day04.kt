typealias World = Map<Point, Char>

private fun parse(input: List<String>): World =
    input
        .flatMapIndexed() { y, line ->
          line.mapIndexed { x, c -> Point(x, y) to c }
        }
        .toMap()

fun take3Steps(direction: Point): List<Point> =
    (1..3).scan(Point(0, 0)) { acc, _ -> acc + direction }

val paths = Point.AROUND.map(::take3Steps)

private fun part1(input: World): Int =
    input
        .filterValues { it == 'X' }
        .keys
        .sumOf { point ->
          paths.count { dir ->
            dir.map { input[point + it] }.joinToString("") == "XMAS"
          }
        }

val cross =
    listOf(
        Point.LEFT + Point.UP,
        Point.RIGHT + Point.DOWN,
        Point.RIGHT + Point.UP,
        Point.LEFT + Point.DOWN,
    )

val validCross = listOf("MSMS", "SMSM", "MSSM", "SMMS")

private fun part2(input: World): Int =
    input
        .filterValues { it == 'A' }
        .keys
        .count { point ->
          validCross.contains(
              cross.map { input[point + it] }.joinToString(""))
        }

fun main() {
  val testInput = parse(rawTestInput)
  val input = parse(readDayInput(4))

  // PART 1
  assertEquals(part1(testInput), 18)
  println("Part1: ${part1(input)}")

  // PART 2
  assertEquals(part2(testInput), 9)
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
