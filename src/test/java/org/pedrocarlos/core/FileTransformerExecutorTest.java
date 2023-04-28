package org.pedrocarlos.core;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
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
import static org.pedrocarlos.core.TransformOperationOptionsObjectMother.createIntNegativeAndReverseWithOneThreadOptions;
import static org.pedrocarlos.core.TransformOperationOptionsObjectMother.createStringTypeWithReverseAndCapitalizedOperationUsingOneThreadOptions;

class FileTransformerExecutorTest {

    private final List<TransformerOperation> transformerOperations = Arrays.asList(new CapitalizeTransformer(), new NegativeTransformer(), new ReverseTransformer(), new ReverseNumbersTransformer());

    private final ReadAndWriteStreamAbstractFactory readAndWriteStreamAbstractFactory = mock(ReadAndWriteStreamAbstractFactory.class);

    private final FileTransformerExecutor executor = new FileTransformerExecutor(transformerOperations, readAndWriteStreamAbstractFactory);


    @Test
    void file_with_int_type_and_operation_neg_reverse() throws IOException {
        mockReader(createFileContent(List.of("102", "10", "-5")));


        var getResult = mockWriter();


        executor.execute(createIntNegativeAndReverseWithOneThreadOptions());

        var list = getResult.get();

        assertEquals(list, List.of("-201", "-1", "5"));
    }


    @Test
    void file_with_string_type_and_operation_reverse_capitalized() throws IOException {
        mockReader(createFileContent(List.of("reverse", "capitalized", "-5")));


        var getResult = mockWriter();

        executor.execute(createStringTypeWithReverseAndCapitalizedOperationUsingOneThreadOptions());

        var list = getResult.get();

        assertEquals(list, List.of("Esrever", "Dezilatipac", "5-"));
    }


    private void mockReader(String string) throws FileNotFoundException {

        when(readAndWriteStreamAbstractFactory.getStreamReader(any(File.class))).then(new Answer<BufferedReader>() {
            @Override
            public BufferedReader answer(InvocationOnMock invocation) throws Throwable {

                return new BufferedReader(new StringReader(string));
            }
        });
    }


    private Supplier<List<String>> mockWriter() throws FileNotFoundException {

        var byteArrayOutputStream = new ByteArrayOutputStream();
        var printStream = new PrintStream(byteArrayOutputStream);

        when(readAndWriteStreamAbstractFactory.getStreamWriter(any(File.class))).then(new Answer<PrintStream>() {
            @Override
            public PrintStream answer(InvocationOnMock invocation) throws Throwable {
                return printStream;
            }
        });

        return () -> {
            return Arrays.stream(byteArrayOutputStream.toString().split("\n")).toList();
        };
    }

    private String createFileContent(List<String> lines) {

        StringBuilder stringBuilder = new StringBuilder();

        lines.forEach(line -> {
            stringBuilder.append(line);
            stringBuilder.append("\r\n");

        });
        return stringBuilder.toString();
    }


}