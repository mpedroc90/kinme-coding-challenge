package org.pedrocarlos.app;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.pedrocarlos.app.converters.InputTypeConverter;
import org.pedrocarlos.app.converters.OperationTypeConverter;
import org.pedrocarlos.core.FileTransformerExecutor;
import org.pedrocarlos.core.InputType;
import org.pedrocarlos.core.Operation;
import org.pedrocarlos.core.TransformersExecutorOptions;

import static picocli.CommandLine.*;

@Command()
public final class FileTransformerCommandLine implements Runnable {

    private final FileTransformerExecutor fileTransformerExecutor;

    @Option(names = {"--input"},
            description = "specifies a plaintext file (UTF8 encoded)",
            required = true
    )
    private File input;
    @Option(
            names = { "--inputtype" },
            description = "specifies the type of data elements.(string, int and double)",
            required = true,
            converter = InputTypeConverter.class
    )
    private InputType inputType;
    @Option(
            names = {"--operations"},
            arity = "1..*",
            description = "describes a pipeline of what to do with data elements. Each operation\n" + "only applies to certain types of data. Implement at least the following operations:\n" + "• capitalize: Capitalizes a string element.\n" + "• reverse: Reverses the characters in a string, or the decimal string\n" + "representation of an integer (1020 becomes 201).\n" + "• neg: Negates an integer or double number (10 becomes -10).",
            required = true,
            split = ",",
            converter = OperationTypeConverter.class)
    private List<Operation> operations;
    @Option(
            names = {"--threads"},
            description = "specifies the number of threads to use when reading the input file and\n" + "applying operations.",
            defaultValue = "1"
    )
    private int threads;
    @Option(
            names = {"--output"},
            description = "specifies a plaintext file (UTF8 encoded)"
    )
    private File output;


    public FileTransformerCommandLine(  @NotNull FileTransformerExecutor fileTransformerExecutor) {
        this.fileTransformerExecutor = fileTransformerExecutor;
    }

    public FileTransformerCommandLine(
            @NotNull FileTransformerExecutor fileTransformerExecutor,
            @NotNull File input,
            InputType inputType,
            @NotNull List<Operation> operations,
            int threads,
            File output
    ) {
        this.fileTransformerExecutor = fileTransformerExecutor;
        this.input = input;
        this.inputType = inputType;
        this.operations = operations;
        this.threads = threads;
        this.output = output;
    }


    public void run() {
        try {
            fileTransformerExecutor.execute(new TransformersExecutorOptions(input, inputType, operations, threads, output));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}