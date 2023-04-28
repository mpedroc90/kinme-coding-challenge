package org.pedrocarlos.core;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TransformOperationOptionsObjectMother {


    public static  TransformersExecutorOptions createIntNegativeAndReverseWithOneThreadOptions() throws IOException {

        var input = File.createTempFile("inputFile", "");
        var output = File.createTempFile("outputFile", "");
        var threads = 1;
        var inputTypes = InputType.Int;
        var operations = List.of(Operation.Neg, Operation.Reverse);

        return new TransformersExecutorOptions(
                input,
                inputTypes,
                operations,
                threads,
                output
        );
    }

    public static  TransformersExecutorOptions createStringTypeWithReverseAndCapitalizedOperationUsingOneThreadOptions() throws IOException {

        var input = File.createTempFile("inputFile", "");
        var output = File.createTempFile("outputFile", "");
        var threads = 1;
        var inputTypes = InputType.String;
        var operations = List.of(Operation.Reverse, Operation.Capitalize);

        return new TransformersExecutorOptions(
                input,
                inputTypes,
                operations,
                threads,
                output
        );
    }
}