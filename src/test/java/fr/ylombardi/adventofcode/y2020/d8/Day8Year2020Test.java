package fr.ylombardi.adventofcode.y2020.d8;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day8Year2020Test {

    Day8Year2020 day = new Day8Year2020();

    @Test
    void part1() throws IOException {
        assertEquals(5, day.part1("src/main/resources/y2020d8-exemple.txt"));
        assertEquals(1, day.part1("src/main/resources/y2020d8.txt"));
    }
}