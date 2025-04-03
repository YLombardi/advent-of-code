package fr.ylombardi.adventofcode.y2024.d8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Year2024Test {

    @Test
    void part1() {
        Day8Year2024 day8Year2024 = new Day8Year2024();
        assertEquals(14, day8Year2024.part1("src/main/resources/input/y2024d8-exemple.txt", false));
        System.out.println("Result part1: " + day8Year2024.part1("src/main/resources/input/y2024d8.txt", false));
    }

    @Test
    void part2() {
        Day8Year2024 day8Year2024 = new Day8Year2024();
        assertEquals(34, day8Year2024.part1("src/main/resources/input/y2024d8-exemple.txt", true));
        System.out.println("Result part2: " + day8Year2024.part1("src/main/resources/input/y2024d8.txt", true));
    }

    @Test
    void part2Bis() {
        Day8Year2024 day8Year2024 = new Day8Year2024();
        assertEquals(12, day8Year2024.part1("src/main/resources/input/y2024d8-exemple2.txt", true));
        System.out.println("Result part2: " + day8Year2024.part1("src/main/resources/input/y2024d8.txt", true));
    }
}