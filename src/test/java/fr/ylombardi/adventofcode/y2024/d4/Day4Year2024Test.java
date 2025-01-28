package fr.ylombardi.adventofcode.y2024.d4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Year2024Test {

    Day4Year2024 day = new Day4Year2024();

    @Test
    void part1() {
        assertEquals(18, day.part1("src/main/resources/y2024d4-exemple.txt"));
        System.out.println("Result part1: " + day.part1("src/main/resources/y2024d4.txt"));
    }

    @Test
    void part2() {
        assertEquals(9, day.part2("src/main/resources/y2024d4-exemple.txt"));
        System.out.println("Result part2: " + day.part2("src/main/resources/y2024d4.txt"));
    }

}