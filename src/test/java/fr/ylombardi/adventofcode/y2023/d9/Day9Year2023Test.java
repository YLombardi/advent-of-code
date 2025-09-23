package fr.ylombardi.adventofcode.y2023.d9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day9Year2023Test {

    private final Day9Year2023 day = new Day9Year2023();

    @Test
    void part1() {
        assertEquals(114, day.part1("src/main/resources/input/y2023d9-exemple.txt"));
        System.out.println(day.part1("src/main/resources/input/y2023d9.txt"));
    }

    @Test
    void part2() {
        assertEquals(2, day.part2("src/main/resources/input/y2023d9-exemple.txt"));
        System.out.println(day.part2("src/main/resources/input/y2023d9.txt"));
    }
}