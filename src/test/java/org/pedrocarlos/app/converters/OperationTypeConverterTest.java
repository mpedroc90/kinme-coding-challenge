package org.pedrocarlos.app.converters;

import org.junit.jupiter.api.Test;
import org.pedrocarlos.app.converters.OperationTypeConverter;
import org.pedrocarlos.core.Operation;

import static org.junit.jupiter.api.Assertions.*;

class OperationTypeConverterTest {
    private final OperationTypeConverter operationTypeConverter = new OperationTypeConverter();

    @Test
    void  givenTypeInLowerCaseItShouldReturnInputTypeEnum() {
        assertEquals(Operation.Reverse,operationTypeConverter.convert("reverse"));
        assertEquals(Operation.Neg,operationTypeConverter.convert("neg"));
        assertEquals(Operation.Capitalize,operationTypeConverter.convert("capitalize"));
    }
}