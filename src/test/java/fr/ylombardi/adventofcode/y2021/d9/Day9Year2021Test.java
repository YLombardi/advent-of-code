package fr.ylombardi.adventofcode.y2021.d9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Year2021Test {

    private final Day9Year2021 day = new Day9Year2021();

    @Test
    void part1() {
        assertEquals(15, day.part1("src/main/resources/input/y2021d9-exemple.txt"));
        System.out.println(day.part1("src/main/resources/input/y2021d9.txt"));
    }

    @Test
    void part2() {
        assertEquals(1134, day.part2("src/main/resources/input/y2021d9-exemple.txt"));
        System.out.println(day.part2("src/main/resources/input/y2021d9.txt"));
    }

}