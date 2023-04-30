package org.pedrocarlos.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class TransformOperationOptionsObjectMother {


    public static  TransformersExecutorOptions file_with_int_type_and_operation_neg_reverse_options(String fileContent) throws IOException {

        var input = File.createTempFile("inputFile", "");
        var output = File.createTempFile("outputFile", "");
        var threads = 1;
        var inputTypes = InputType.Int;
        var operations = List.of(Operation.Neg, Operation.Reverse);

        try (var file  = Files.newBufferedWriter(input.toPath())) {
                file.write(fileContent);
                file.newLine();
        }


        return new TransformersExecutorOptions(
                input,
                inputTypes,
                operations,
                threads,
                output
        );
    }

    public static  TransformersExecutorOptions file_with_string_type_and_operation_reverse_capitalized_options(String fileContent) throws IOException {

        var input = File.createTempFile("inputFile", "");
        var output = File.createTempFile("outputFile", "");
        var threads = 1;
        var inputTypes = InputType.String;
        var operations = List.of(Operation.Reverse, Operation.Capitalize);


        try (var file  = Files.newBufferedWriter(input.toPath())) {
            file.write(fileContent);
            file.newLine();
        }

        return new TransformersExecutorOptions(
                input,
                inputTypes,
                operations,
                threads,
                output
        );
    }

     static  TransformersExecutorOptions file_with_string_type_and_operation_reverse_capitalized_with_nth_threads(String fileContent , int threadsNumber) throws IOException {

        var input = File.createTempFile("inputFile", "");
        var output = File.createTempFile("outputFile", "");

        var inputTypes = InputType.String;
        var operations = List.of(Operation.Reverse, Operation.Capitalize);

        try (var file  = Files.newBufferedWriter(input.toPath())) {
            file.write(fileContent);
            file.newLine();
        }

        return new TransformersExecutorOptions(
                input,
                inputTypes,
                operations,
                threadsNumber,
                output
        );
    }
}