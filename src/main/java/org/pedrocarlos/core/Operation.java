package org.pedrocarlos.core;

import java.util.Arrays;


public enum Operation {
    Capitalize,
    Neg,
    Reverse;

    public static Operation from(String value) {
        return Arrays.stream(Operation.values())
                .filter(typeValue -> typeValue.name().toLowerCase().equals(value)).findFirst().orElseThrow();
    }
}