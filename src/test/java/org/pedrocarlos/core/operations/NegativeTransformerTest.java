package org.pedrocarlos.core.operations;

import org.junit.jupiter.api.Test;
import org.pedrocarlos.core.operations.NegativeTransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NegativeTransformerTest {


    private final NegativeTransformer capitalizeTransformer = new NegativeTransformer();

    @Test
    void shouldReturnSameStringIfItIsNotANumber() {
        assertEquals("test", capitalizeTransformer.transform("test"));
    }

    @Test
    void shouldReturnANegativeNumberIfItisGivenAPositive() {
        assertEquals("-10", capitalizeTransformer.transform("10"));
    }


    @Test
    void shouldReturnAPositiveNumberIfItisGivenANegative() {
        assertEquals("10", capitalizeTransformer.transform("-10"));
    }


    @Test
    void shouldReturnTheOppostiveNumberIfTheNumberIsOneDigit() {
        assertEquals("1", capitalizeTransformer.transform("-1"));
    }

    @Test
    void shouldReturnZeroIfTheNumberIsZero() {
        assertEquals("0", capitalizeTransformer.transform("0"));
    }

    @Test
    void shouldReturnZeroIfItISGivenZeroWithServeralDigits() {
        assertEquals("000000", capitalizeTransformer.transform("000000"));
    }


    @Test
    void shouldReturnZeroIfItISGivenNegativeZero() {
        assertEquals("0000", capitalizeTransformer.transform("-0000"));
    }

    @Test
    void shouldReturnEmptyIfItIsGivenEmptyString() {
        assertEquals("", capitalizeTransformer.transform(""));
    }
}