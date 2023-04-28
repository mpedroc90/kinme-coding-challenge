package org.pedrocarlos.app.converters;

import org.junit.jupiter.api.Test;
import org.pedrocarlos.app.converters.InputTypeConverter;
import org.pedrocarlos.core.InputType;

import static org.junit.jupiter.api.Assertions.*;

class InputTypeConverterTest {

    private InputTypeConverter inputTypeConverter = new InputTypeConverter();
    @Test
    void  givenTypeInLowerCaseItShouldReturnInputTypeEnum() {

        assertEquals(InputType.Int,inputTypeConverter.convert("int"));
        assertEquals(InputType.Double,inputTypeConverter.convert("double"));
        assertEquals(InputType.String,inputTypeConverter.convert("string"));
    }
}