package fr.ylombardi.adventofcode.y2018.d9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Year2018Test {

    private final Day9Year2018 day = new Day9Year2018();

    @Test
    void part1() {
        assertEquals(8317, day.part1(10, 1618));
        assertEquals(146373, day.part1(13, 7999));
        assertEquals(2764, day.part1(17, 1104));
        assertEquals(54718, day.part1(21, 6111));
        assertEquals(37305, day.part1(30, 5807));
        System.out.println(day.part1(400, 71864));
    }

    @Test
    void part2() {
        System.out.println(day.part2(400, 7186400));
    }

}