package org.pedrocarlos.core.operations;

import java.util.List;
import org.pedrocarlos.core.InputType;
import org.pedrocarlos.core.Operation;

public class CapitalizeTransformer implements TransformerOperation {
    @Override
    public Operation getOperation() {
        return Operation.Capitalize;
    }

    @Override
    public List<InputType> supportedTypes() {
        return List.of(InputType.String);
    }

    @Override
    public String transform(String line) {
        if(line.isEmpty()) return line;

        if (line.charAt(0) >= 'a' && line.charAt(0) <= 'z')
            return ((char) (line.charAt(0) + 'A' - 'a')) + line.substring(1);
        return line;
    }
}