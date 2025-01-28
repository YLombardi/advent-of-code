package fr.ylombardi.adventofcode.y2021.d7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day7Year2021Test {

    private final Day7Year2021 day = new Day7Year2021();

    @Test
    void part1() {
        System.out.println(day.part1("src/main/resources/y2021d7.csv"));
        Assertions.assertEquals(37, day.part1("src/main/resources/y2021d7-exemple.csv"));
    }

    @Test
    void part2() {
        System.out.println(day.part2("src/main/resources/y2021d7.csv"));
        Assertions.assertEquals(168, day.part2("src/main/resources/y2021d7-exemple.csv"));
//        System.out.println(day.part2WithStream("src/main/resources/y2021d7.csv"));
        //Assertions.assertEquals(168, day.part2WithStream("src/main/resources/y2021d7-exemple.csv"));
    }
}