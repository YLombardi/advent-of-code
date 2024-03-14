package fr.ylombardi.adventofcode.y2018.d7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Year2017Test {

    private final Day7Year2017 day7Year2017 = new Day7Year2017();

    @Test
    void part1() {
        assertEquals("CABDFE", day7Year2017.part1("src/main/resources/exemple.txt"));
        System.out.println(day7Year2017.part1("src/main/resources/input.txt"));
    }

    @Test
    void part2() {
        assertEquals(15, day7Year2017.part2("src/main/resources/exemple.txt", 2, 0));
        System.out.println(day7Year2017.part2("src/main/resources/input.txt", 5, 60));
    }
}