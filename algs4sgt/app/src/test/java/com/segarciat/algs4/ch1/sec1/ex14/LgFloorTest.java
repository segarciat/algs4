package com.segarciat.algs4.ch1.sec1.ex14;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.segarciat.algs4.ch1.sec1.ex14.LgFloor.lg;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LgFloorTest {

    @Test
    @DisplayName("Invalid input")
    void testLg_whenInvalidInput_shouldThrow() {
        assertAll(
                () ->  assertThrows(IllegalArgumentException.class, () -> lg(-1)),
                () ->  assertThrows(IllegalArgumentException.class, () -> lg(0))
        );
    }

    @Test
    @DisplayName("Powers of 2")
    void testLg_whenNIsAPowerOf2_shouldReturnPositionOfMsBit() {
        assertAll(
                () -> assertEquals(0, lg(1)),
                () -> assertEquals(1, lg(2)),
                () -> assertEquals(2, lg(4)),
                () -> assertEquals(3, lg(8)),
                () -> assertEquals(4, lg(16))
        );
    }

    @Test
    @DisplayName("Not a power of 2")
    void testLg_whenNIsNotAPowerOf2_shouldReturnPositionOfMsBit() {
        assertAll(
                () -> assertEquals(1, lg(3)),
                () -> assertEquals(2, lg(5)),
                () -> assertEquals(2, lg(6)),
                () -> assertEquals(2, lg(7)),
                () -> assertEquals(3, lg(9)),
                () -> assertEquals(3, lg(10)),
                () -> assertEquals(3, lg(15))
        );
    }
}