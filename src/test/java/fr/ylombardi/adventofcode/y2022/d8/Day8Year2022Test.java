package fr.ylombardi.adventofcode.y2022.d8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day8Year2022Test {

    Day8Year2022 day = new Day8Year2022();

    @Test
    void part1() {
        assertEquals(21, day.part1("src/main/resources/input/y2022d8-exemple.txt"));
        System.out.println("Result part1: " + day.part1("src/main/resources/input/y2022d8.txt"));
    }

    @Test
    void part2() {
        assertEquals(8, day.part2("src/main/resources/input/y2022d8-exemple.txt"));
        System.out.println("Result part2: " + day.part2("src/main/resources/input/y2022d8.txt"));
    }

}