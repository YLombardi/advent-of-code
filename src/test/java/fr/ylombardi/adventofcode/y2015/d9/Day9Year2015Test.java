package fr.ylombardi.adventofcode.y2015.d9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day9Year2015Test {

    Day9Year2015 day = new Day9Year2015();

    @Test
    void part1() {
        Assertions.assertEquals(605, day.part1("src/main/resources/input/y2015d9-exemple.txt"));
        System.out.println(day.part1("src/main/resources/input/y2015d9.txt"));
    }

    @Test
    void part2() {
        Assertions.assertEquals(982, day.part2("src/main/resources/input/y2015d9-exemple.txt"));
        System.out.println(day.part2("src/main/resources/input/y2015d9.txt"));
    }
}