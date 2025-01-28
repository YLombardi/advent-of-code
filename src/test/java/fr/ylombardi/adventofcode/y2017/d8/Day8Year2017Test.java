package fr.ylombardi.adventofcode.y2017.d8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day8Year2017Test {

    Day8Year2017 day = new Day8Year2017();

    @Test
    void part1() {
        Assertions.assertEquals(1, day.part1("src/main/resources/y2017d8-exemple.txt"));
        System.out.println(day.part1("src/main/resources/y2017d8.txt"));
    }

    @Test
    void part2() {
        Assertions.assertEquals(10, day.part2("src/main/resources/y2017d8-exemple.txt"));
        System.out.println(day.part2("src/main/resources/y2017d8.txt"));
    }

}