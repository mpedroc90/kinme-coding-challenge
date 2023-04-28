package org.pedrocarlos.core.operations;

import java.util.List;
import org.pedrocarlos.core.InputType;
import org.pedrocarlos.core.Operation;

public interface TransformerOperation {
    Operation getOperation();

    String transform(String line);

    List<InputType> supportedTypes();
}