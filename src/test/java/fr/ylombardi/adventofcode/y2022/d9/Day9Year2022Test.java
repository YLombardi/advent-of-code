package fr.ylombardi.adventofcode.y2022.d9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day9Year2022Test {

    @Test
    void part1() {
        Day9Year2022 day9 = new Day9Year2022();
        int result = day9.exo("src/main/resources/input/y2022d9-exemple.txt", 2);
        assertEquals(13, result);
        System.out.println(day9.exo("src/main/resources/input/y2022d9.txt", 2));
    }

    @Test
    void exo() {
        Day9Year2022 day9 = new Day9Year2022();
        int result = day9.exo("src/main/resources/input/y2022d9-exemple.txt", 10);
        assertEquals(1, result);
        System.out.println(day9.exo("src/main/resources/input/y2022d9.txt", 10));
    }
}