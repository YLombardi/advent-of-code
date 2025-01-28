package fr.ylombardi.adventofcode.y2018.d7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Year2018Test {

    private final Day7Year2018 day7Year2018 = new Day7Year2018();

    @Test
    void part1() {
        assertEquals("CABDFE", day7Year2018.part1("src/main/resources/y2018d7-exemple.txt"));
        System.out.println(day7Year2018.part1("src/main/resources/y2018d7.txt"));
    }

    @Test
    void part2() {
        assertEquals(15, day7Year2018.part2("src/main/resources/y2018d7-exemple.txt", 2, 0));
        System.out.println(day7Year2018.part2("src/main/resources/y2018d7.txt", 5, 60));
    }
}