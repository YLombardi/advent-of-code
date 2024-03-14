package fr.ylombardi.adventofcode.y2018.d7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkersTest {

    @Test
    void getTime() {
        Assertions.assertEquals(1, Workers.getTime("A"));
        Assertions.assertEquals(12, Workers.getTime("L"));
        Assertions.assertEquals(26, Workers.getTime("Z"));
    }
}