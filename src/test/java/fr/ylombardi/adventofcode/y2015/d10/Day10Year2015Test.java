package fr.ylombardi.adventofcode.y2015.d10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day10Year2015Test {

    private final Day10Year2015 instance = new Day10Year2015();

    @Test
    void part1() {
        assertEquals("1", instance.part1("1", 0));
        assertEquals("11", instance.part1("1", 1));
        assertEquals("21", instance.part1("1", 2));
        assertEquals("1211", instance.part1("1", 3));
        assertEquals("111221", instance.part1("1", 4));
        System.out.println("Part 1: " + instance.part1("1113222113", 50).length());
    }
}