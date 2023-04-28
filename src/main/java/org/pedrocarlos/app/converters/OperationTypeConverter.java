package org.pedrocarlos.app.converters;

import org.pedrocarlos.core.Operation;
import picocli.CommandLine;

public class OperationTypeConverter implements CommandLine.ITypeConverter<Operation> {
    @Override
    public Operation convert(String value){
        return Operation.from(value);
    }
}