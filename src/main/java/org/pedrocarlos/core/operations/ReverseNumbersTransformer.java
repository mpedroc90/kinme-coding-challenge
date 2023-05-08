package org.pedrocarlos.core.operations;

import java.util.List;
import org.pedrocarlos.core.Operation;
import org.pedrocarlos.core.utils.Numbers;
import org.pedrocarlos.core.InputType;

public class ReverseNumbersTransformer implements TransformerOperation {
    @Override
    public Operation getOperation() {
        return Operation.Reverse;
    }

    @Override
    public List<InputType> supportedTypes() {
        return List.of(InputType.Double, InputType.Int);
    }

    @Override
    public String transform(String line) {
        if (!Numbers.isNumeric(line)) return line;

        StringBuilder builder = new StringBuilder();

        int charIndex = line.length() - 1;

        while (charIndex>=0 && line.charAt(charIndex) == '0') charIndex--;

       for (; charIndex >= 0 && (
               line.charAt(charIndex) >= '0' && line.charAt(charIndex) <= '9' || line.charAt(charIndex) == '.'); charIndex--) {
            builder.append(line.charAt(charIndex));
        }

        return (Numbers.isNegative(line) ? "-" : "") + builder;
    }
}