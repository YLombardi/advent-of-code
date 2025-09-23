package fr.ylombardi.adventofcode.y2017.d10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day10Year2017Test {

    Day10Year2017 day = new Day10Year2017();

    @Test
    void part1() {
        assertEquals(12, day.part1(List.of(3, 4, 1, 5), 5));
        System.out.println(day.part1(List.of(97,167,54,178,2,11,209,174,119,248,254,0,255,1,64,190), 256));
    }

    @Test
    void part2() {
        assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", day.part2("1,2,3"));
        assertEquals("a2582a3a0e66e6e86e3812dcb672a272", day.part2(""));
        assertEquals("33efeb34ea91902bb2f59c9920caa6cd", day.part2("AoC 2017"));
        assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", day.part2("1,2,4"));
        System.out.println(day.part2("97,167,54,178,2,11,209,174,119,248,254,0,255,1,64,190"));
    }
}