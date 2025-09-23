package fr.ylombardi.adventofcode.y2024.d9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day9Year2024Test {

    private final Day9Year2024 test = new Day9Year2024();

    @Test
    void part1() {
        assertEquals(1928, test.part1("src/main/resources/input/y2024d9-exemple.txt"));
        System.out.println("Part 1: " + test.part1("src/main/resources/input/y2024d9.txt"));
    }

    @Test
    void part2() {
        assertEquals(2858, test.part2("src/main/resources/input/y2024d9-exemple.txt"));
        System.out.println("Part 1: " + test.part2("src/main/resources/input/y2024d9.txt"));
    }
}