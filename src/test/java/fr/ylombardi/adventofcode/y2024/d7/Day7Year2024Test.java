package fr.ylombardi.adventofcode.y2024.d7;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Year2024Test {

    @Test
    void part1() {
        Day7Year2024 day7Year2024 = new Day7Year2024();
        assertEquals(BigInteger.valueOf(3749), day7Year2024.part1("src/main/resources/input/y2024d7-exemple.txt", false));
        System.out.println("Result part1: " + day7Year2024.part1("src/main/resources/input/y2024d7.txt", false));
    }

    @Test
    void part2() {
        Day7Year2024 day7Year2024 = new Day7Year2024();
        assertEquals(BigInteger.valueOf(11387), day7Year2024.part1("src/main/resources/input/y2024d7-exemple.txt", true));
        System.out.println("Result part2: " + day7Year2024.part1("src/main/resources/input/y2024d7.txt", true));
    }
}