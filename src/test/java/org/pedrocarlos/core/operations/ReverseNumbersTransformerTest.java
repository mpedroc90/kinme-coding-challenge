package org.pedrocarlos.core.operations;

import org.junit.jupiter.api.Test;
import org.pedrocarlos.core.operations.ReverseNumbersTransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseNumbersTransformerTest {


    private final ReverseNumbersTransformer reverseNumbersTransformer = new ReverseNumbersTransformer();

    @Test
    void shouldReturnTheReverseNumber() {
        assertEquals("12345", reverseNumbersTransformer.transform("54321"));
    }


    @Test
    void shouldReturnTheReverseNumberWithoutLeadingZeros() {
        assertEquals("345", reverseNumbersTransformer.transform("54300"));
    }


    @Test
    void shouldKeepNegativeSignAndReverseTheDigits() {
        assertEquals("-12345", reverseNumbersTransformer.transform("-54321"));
    }

    @Test
    void shouldKeepNegativeSignAndRemoveLeadingZerosReverseTheDigits() {
        assertEquals("-345", reverseNumbersTransformer.transform("-54300"));
    }


    @Test
    void shouldReturnSameStringIfItIsNotANumber() {
        assertEquals("-543Test00", reverseNumbersTransformer.transform("-543Test00"));
    }


    @Test
    void shouldReverseADoubleNumber() {
        assertEquals("-5.4321", reverseNumbersTransformer.transform("-1234.5"));
    }

}