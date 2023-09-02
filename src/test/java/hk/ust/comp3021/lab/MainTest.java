package hk.ust.comp3021.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void add() {
        assertEquals(3, Main.add(1, 2));
    }
}