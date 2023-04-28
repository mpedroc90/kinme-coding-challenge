package org.pedrocarlos.app.converters;

import org.pedrocarlos.core.InputType;
import picocli.CommandLine;

public class InputTypeConverter implements CommandLine.ITypeConverter<InputType> {
    @Override
    public InputType convert(String value){
        return InputType.from(value);
    }
}