package org.pedrocarlos.core.operations;

import org.junit.jupiter.api.Test;
import org.pedrocarlos.core.operations.CapitalizeTransformer;

import static org.junit.jupiter.api.Assertions.*;

class CapitalizeTransformerTest {
    private final CapitalizeTransformer capitalizeTransformer = new CapitalizeTransformer();

    @Test
    void shouldCapitalizeAString() {
        assertEquals("Test", capitalizeTransformer.transform("test"));
    }


    @Test
    void shouldReturnSameTextIfStringDoesNotStartWithLetter() {
        assertEquals("1test", capitalizeTransformer.transform("1test"));
    }

    @Test
    void shouldReturnEmptyIfStringIsEmpty() {
        assertEquals("", capitalizeTransformer.transform(""));
    }
}