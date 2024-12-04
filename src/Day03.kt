sealed class Instruction {
  object Do : Instruction()

  object Dont : Instruction()

  data class Mul(val a: Int, val b: Int) : Instruction()
}

private fun parse(input: List<String>) =
    Regex("""do\(\)|don't\(\)|mul\((\d{1,3}),(\d{1,3})\)""")
        .findAll(input.joinToString(""))
        .map { matchResult ->
          when (matchResult.groupValues[0]) {
            "do()" -> Instruction.Do
            "don't()" -> Instruction.Dont
            else ->
                Instruction.Mul(
                    matchResult.groupValues[1].toInt(),
                    matchResult.groupValues[2].toInt())
          }
        }
        .toList()

private fun part1(input: List<Instruction>): Int =
    input.filterIsInstance<Instruction.Mul>().sumOf { it.a * it.b }

private fun part2(input: List<Instruction>): Int =
    input
        .fold(0 to true) { (acc, enabled), instruction ->
          when (instruction) {
            is Instruction.Do -> acc to true
            is Instruction.Dont -> acc to false
            is Instruction.Mul -> {
              if (enabled) (acc + (instruction.a * instruction.b) to enabled)
              else acc to enabled
            }
          }
        }
        .first

fun main() {
  val testInput = parse(rawTestInput)
  val input = parse(readDayInput(3))

  // PART 1
  assertEquals(part1(testInput), 161)
  println("Part1: ${part1(input)}")

  // PART 2
  assertEquals(part2(testInput), 48)
  println("Part2: ${part2(input)}")
}

private val rawTestInput =
    """
    xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
 """
        .trimIndent()
        .lines()
