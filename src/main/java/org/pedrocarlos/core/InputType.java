package org.pedrocarlos.core;

import java.util.Arrays;

public enum InputType {
    Int, String, Double;

    public static InputType from(String type) {
        return Arrays.stream(InputType.values())
                .filter(typeValue -> typeValue.name().toLowerCase().equals(type))
                .findFirst()
                .orElseThrow();
    }
}