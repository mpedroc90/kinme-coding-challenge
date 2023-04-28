package org.pedrocarlos.core.operations;

import java.util.List;
import org.pedrocarlos.core.InputType;
import org.pedrocarlos.core.Operation;

import static org.pedrocarlos.core.utils.Numbers.*;

public class NegativeTransformer implements TransformerOperation {
    @Override
    public Operation getOperation() {
        return Operation.Neg;
    }

    @Override
    public List<InputType> supportedTypes() {
        return List.of(InputType.Double, InputType.Int);
    }

    @Override
    public String transform(String line) {
        if (!isNumeric(line) || isZero(line)) return line;

        return isNegative(line) ? line.substring(1) : ("-" + line);


    }
}