package org.pedrocarlos.core.operations;

import org.junit.jupiter.api.Test;
import org.pedrocarlos.core.operations.ReverseTransformer;

import static org.junit.jupiter.api.Assertions.*;

class ReverseTransformerTest {

    private final ReverseTransformer reverseNumbersTransformer = new ReverseTransformer();

    @Test
    void shouldReturnTheReverseATextWithEvenCharacters() {
        assertEquals("test", reverseNumbersTransformer.transform("tset"));
    }


    @Test
    void shouldReturnTheReverseATextWitOddCharacters() {
        assertEquals("te1st", reverseNumbersTransformer.transform("ts1et"));
    }
}