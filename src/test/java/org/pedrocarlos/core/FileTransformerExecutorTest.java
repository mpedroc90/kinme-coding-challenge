package org.pedrocarlos.core;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.pedrocarlos.core.operations.CapitalizeTransformer;
import org.pedrocarlos.core.operations.NegativeTransformer;
import org.pedrocarlos.core.operations.ReverseNumbersTransformer;
import org.pedrocarlos.core.operations.ReverseTransformer;
import org.pedrocarlos.core.operations.TransformerOperation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.pedrocarlos.core.TransformOperationOptionsObjectMother.file_with_int_type_and_operation_neg_reverse_options;
import static org.pedrocarlos.core.TransformOperationOptionsObjectMother.file_with_string_type_and_operation_reverse_capitalized_options;
import static org.pedrocarlos.core.TransformOperationOptionsObjectMother.file_with_string_type_and_operation_reverse_capitalized_with_nth_threads;

class FileTransformerExecutorTest {

    private final List<TransformerOperation> transformerOperations = Arrays.asList(new CapitalizeTransformer(), new NegativeTransformer(), new ReverseTransformer(), new ReverseNumbersTransformer());

    private final ReadAndWriteStreamAbstractFactory readAndWriteStreamAbstractFactory = mock(ReadAndWriteStreamAbstractFactory.class);

    private final FileTransformerExecutor executor = new FileTransformerExecutor(transformerOperations, readAndWriteStreamAbstractFactory);


    @Test
    void file_with_int_type_and_operation_neg_reverse() throws IOException {
        var options = file_with_int_type_and_operation_neg_reverse_options(createFileContent(List.of("102", "10", "-5")));
        mockReader(options.input());


        var getResult = mockWriter();


        executor.execute(options);

        var list = getResult.get();


        var values = extractValuesFromResult(getResult.get());

        assertEquals(List.of("-201", "-1", "5"), values);

    }


    @Test
    void file_with_string_type_and_operation_reverse_capitalized() throws IOException {

        var options = file_with_string_type_and_operation_reverse_capitalized_options(createFileContent(List.of("reverse", "capitalized", "-5")));
        mockReader(options.input());


        var getResult = mockWriter();

        executor.execute(options);

        var values = extractValuesFromResult(getResult.get());
        assertEquals(values, List.of("Esrever", "Dezilatipac", "5-"));
    }


    @Test
    void file_with_string_type_and_operation_reverse_capitalized_with_3_threads() throws IOException {

        var options = file_with_string_type_and_operation_reverse_capitalized_with_nth_threads(createFileContent(List.of("reverse", "capitalized", "-5", "world")), 3);
        mockReader(options.input());

        var getResult = mockWriter();
        executor.execute(options);
        var result = getResult.get();
        var values = extractValuesFromResult(result);
        assertEquals(values, List.of("Esrever", "Dezilatipac", "5-", "Dlrow"));

    }

    List<String> extractValuesFromResult(List<String> lines) {
        return lines.stream().map(line -> line.split(" ")[0]).collect(Collectors.toList());
    }

    List<String> extractThreadsFromResult(List<String> lines) {
        return lines.stream().map(line -> {

            var values = line.split(" ");
            return values[values.length - 1];
        }).collect(Collectors.toList());
    }


    private void mockReader(File input) throws FileNotFoundException {
        when(readAndWriteStreamAbstractFactory.getStreamReader(any(File.class))).then((Answer<BufferedReader>) invocation -> new BufferedReader(new FileReader(input)));
    }


    private Supplier<List<String>> mockWriter() throws FileNotFoundException {

        var byteArrayOutputStream = new ByteArrayOutputStream();
        var printStream = new PrintStream(byteArrayOutputStream);

        when(readAndWriteStreamAbstractFactory.getStreamWriter(any(File.class))).then((Answer<PrintStream>) invocation -> printStream);

        return () -> Arrays.stream(byteArrayOutputStream.toString().split("\n")).toList();
    }

    private String createFileContent(List<String> lines) {

        StringBuilder stringBuilder = new StringBuilder();


        AtomicBoolean first = new AtomicBoolean(true);
        lines.forEach(line -> {
            if (!first.get()) {
                stringBuilder.append("\n");
            }
            first.set(false);
            stringBuilder.append(line);
        });
        return stringBuilder.toString();
    }


}