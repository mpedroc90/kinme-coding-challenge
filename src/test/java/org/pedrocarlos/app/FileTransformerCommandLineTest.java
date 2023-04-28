package org.pedrocarlos.app;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

import org.mockito.internal.matchers.Any;
import org.pedrocarlos.core.FileTransformerExecutor;
import org.pedrocarlos.core.InputType;
import org.pedrocarlos.core.Operation;
import org.pedrocarlos.core.TransformersExecutorOptions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class FileTransformerCommandLineTest {

    @Test
    void shouldCallTransformerExecutor() throws IOException {

        //Arrange
        var transformerExecutor = mock(FileTransformerExecutor.class);

        var input = File.createTempFile("inputFile", "");
        var output = File.createTempFile("outputFile", "");
        var threads = 3;
        var inputTypes = InputType.Int;
        var operations = List.of(Operation.Neg, Operation.Capitalize);

        var transformerCommandLine = new FileTransformerCommandLine(
                transformerExecutor,
                input,
                inputTypes,
                operations,
                threads,
                output
        );

        // Act
        transformerCommandLine.run();

        //Assert
        verify(transformerExecutor)
            .execute(new TransformersExecutorOptions(
                    input,
                    inputTypes,
                    operations,
                    threads,
                    output)
            );


    }

}