package fr.ylombardi.adventofcode.y2024.d5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Year2024Test {

    Day5Year2024 day = new Day5Year2024();

    @Test
    void part1() {
        assertEquals(143, day.part1("src/main/resources/input/y2024d5-exemple.txt"));
        System.out.println("Result part1: " + day.part1("src/main/resources/input/y2024d5.txt"));
    }

    @Test
    void part2() {
        assertEquals(123, day.part2("src/main/resources/input/y2024d5-exemple.txt"));
        System.out.println("Result part2: " + day.part2("src/main/resources/input/y2024d5.txt"));
    }

}