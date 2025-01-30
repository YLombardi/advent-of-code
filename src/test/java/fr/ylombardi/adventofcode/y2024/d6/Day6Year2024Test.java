package fr.ylombardi.adventofcode.y2024.d6;

import fr.ylombardi.adventofcode.y2024.d6.emoji.Day6Year2024Bis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day6Year2024Test {

    @Test
    void part1() {
        Day6Year2024 day6Year2024 = new Day6Year2024();
        assertEquals(41, day6Year2024.part1("src/main/resources/input/y2024d6-exemple.txt"));
        System.out.println("Result part1: " + day6Year2024.part1("src/main/resources/input/y2024d6.txt"));
    }

    @Test
    void part2() {
        Day6Year2024 day6Year2024 = new Day6Year2024();
        assertEquals(6, day6Year2024.part2("src/main/resources/input/y2024d6-exemple.txt"));
        System.out.println("Result part2: " + day6Year2024.part2("src/main/resources/input/y2024d6.txt"));
    }

    @Test
    void part1Bis() {
        Day6Year2024Bis day6Year2024 = new Day6Year2024Bis();
//        assertEquals(41, day6Year2024.part1("src/main/resources/input/y2024d6-exemple.txt"));
        System.out.println("Result part1: " + day6Year2024.part1("src/main/resources/input/y2024d6.txt"));
    }
}