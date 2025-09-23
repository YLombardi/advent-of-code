package fr.ylombardi.adventofcode.y2016.d10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day10Year2016Test {

    Day10Year2016 day10Year2016 = new Day10Year2016();
    Day10Year2016V2 day10Year2016V2 = new Day10Year2016V2();

    @Test
    void part1() {
        assertEquals(2, day10Year2016.part1("src/main/resources/input/y2016d10-exemple.txt", 5, 2, true));
        //assertEquals(2, day10Year2016V2.part1("src/main/resources/input/y2016d10-exemple.txt", 5, 2, true));
        System.out.println(day10Year2016.part1("src/main/resources/input/y2016d10.txt", 17, 61, true));
    }

    @Test
    void part2() {
        assertEquals(30, day10Year2016.part1("src/main/resources/input/y2016d10-exemple.txt", 5, 2, false));
        //assertEquals(30, day10Year2016V2.part1("src/main/resources/input/y2016d10-exemple.txt", 5, 2, false));
        System.out.println(day10Year2016.part1("src/main/resources/input/y2016d10.txt", 17, 61, false));
    }
}