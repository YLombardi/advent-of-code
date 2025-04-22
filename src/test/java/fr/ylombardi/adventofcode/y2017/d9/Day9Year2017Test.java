package fr.ylombardi.adventofcode.y2017.d9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day9Year2017Test {

    Day9Year2017 day = new Day9Year2017();

    @Test
    void part1() {
        Assertions.assertEquals(3, day.part1("src/main/resources/input/y2017d9-exemple.txt"));
        System.out.println(day.part1("src/main/resources/input/y2017d9.txt"));
    }

    @Test
    void part2() {
        Assertions.assertEquals(3, day.part1("src/main/resources/input/y2017d9-exemple.txt"));
        System.out.println(day.part2("src/main/resources/input/y2017d9.txt"));
    }

}