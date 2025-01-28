package fr.ylombardi.adventofcode.y2022.d7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class Day7Year2022Test {

    Day7Year2022 day = new Day7Year2022();

    @Test
    void part1() {
        Assertions.assertEquals(95437,  day.part1("src/main/resources/y2022d7-y2018d7-exemple.txt"));
        System.out.println(day.part1("src/main/resources/y2022d7.txt"));
    }

    @Test
    void part2() {
        Assertions.assertEquals(24933642,  day.part2("src/main/resources/y2022d7-y2018d7-exemple.txt"));
        System.out.println(day.part2("src/main/resources/y2022d7.txt"));
    }
}