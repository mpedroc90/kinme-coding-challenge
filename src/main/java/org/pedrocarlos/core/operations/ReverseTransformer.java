package org.pedrocarlos.core.operations;

import java.util.List;
import org.pedrocarlos.core.InputType;
import org.pedrocarlos.core.Operation;

public class ReverseTransformer implements TransformerOperation {
    @Override
    public Operation getOperation() {
        return Operation.Reverse;
    }

    @Override
    public List<InputType> supportedTypes() {
        return List.of(InputType.String);
    }

    @Override
    public String transform(String line) {
        StringBuilder builder = new StringBuilder();

        for (int charIndex = line.length() - 1; charIndex >= 0; charIndex--) {
            builder.append(line.charAt(charIndex));
        }

        return builder.toString();
    }
}