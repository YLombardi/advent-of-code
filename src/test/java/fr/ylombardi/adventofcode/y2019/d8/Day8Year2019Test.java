package fr.ylombardi.adventofcode.y2019.d8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Year2019Test {

    private final Day8Year2019 day = new Day8Year2019();

    @Test
    void part1() {
        assertEquals(1, day.exo("src/main/resources/input/y2019d8-exemple.txt",3, 2));
        System.out.println(day.exo("src/main/resources/input/y2019d8.txt", 25, 6));
    }

}