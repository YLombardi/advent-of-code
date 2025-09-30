package fr.ylombardi.adventofcode.y2018.d10;

import org.junit.jupiter.api.Test;

class Day10Year2018Test {

    Day10Year2018 day10Year2018 = new Day10Year2018();

    @Test
    void part1() {
        day10Year2018.part1("src/main/resources/input/y2018d10-exemple.txt", 4);
        day10Year2018.part1("src/main/resources/input/y2018d10.txt", 11000);
    }
}