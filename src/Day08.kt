private typealias Antennas = Map<Char, List<Point>>

private fun parse(input: List<String>): Antennas = input
    .flatMapIndexed { y, line ->
        line.mapIndexedNotNull { x, c ->
            if (c != '.') c to Point(x, y) else null
        }
    }
    .groupBy({ it.first }, { it.second })


private fun part1(input: Antennas): Int {
    input.map { (freq, antennas) ->
        val pairs = antennas.flatMap { a ->
            antennas.mapNotNull { b ->
                if (a != b) a to b else null
            }
        }

        val distances = pairs.map { (a, b) ->
            listOf(a )
        }
    }
    
    return 0
}

private fun part2(input: Antennas): Int {
    return 0
}

fun main() {
    val testInput = parse(rawTestInput)
    println(testInput)
    val input = parse(readDayInput(8))

    // PART 1
    assertEquals(part1(testInput), 14)
    println("Part1: ${part1(input)}")

    // PART 2
    assertEquals(part2(testInput), 0)
    println("Part2: ${part2(input)}")
}

private val rawTestInput = """
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
""".trimIndent().lines()