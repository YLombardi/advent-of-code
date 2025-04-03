package fr.ylombardi.adventofcode.y2016.d9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day9Year2016Test {

    Day9Year2016 day = new Day9Year2016();

    @Test
    void part1() {
        Assertions.assertEquals(18, day.part1("src/main/resources/input/y2016d9-exemple.txt"));
        System.out.println(day.part1("src/main/resources/input/y2016d9.txt"));
    }

    @Test
    void part2Naive() {
        Assertions.assertEquals(20, day.part2Naive("src/main/resources/input/y2016d9-exemple.txt"));
        System.out.println(day.part2Naive("src/main/resources/input/y2016d9.txt"));
    }

    @Test
    void part2() {
        Assertions.assertEquals(20, day.part2("src/main/resources/input/y2016d9-exemple.txt"));
        System.out.println(day.part2("src/main/resources/input/y2016d9.txt"));
    }
}