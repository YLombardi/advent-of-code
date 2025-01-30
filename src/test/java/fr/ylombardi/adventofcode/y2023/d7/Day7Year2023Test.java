package fr.ylombardi.adventofcode.y2023.d7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day7Year2023Test {

    private final Day7Year2023 day = new Day7Year2023();

    @Test
    void part1() {
        assertEquals(6440, day.part1("src/main/resources/input/y2023d7-exemple.txt"));
        System.out.println(day.part1("src/main/resources/input/y2023d7.txt"));
    }

    @Test
    void part2() {
        assertEquals(5905, day.part2("src/main/resources/input/y2023d7-exemple.txt"));
        System.out.println(day.part1("src/main/resources/input/y2023d7.txt"));
    }
}