package org.pedrocarlos.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import org.pedrocarlos.core.operations.TransformerOperation;


public final class FileTransformerExecutor {

    /**
     * List of TransformerOperations that can be applied to the input file.
     */
    private final List<TransformerOperation> transformerOperations;


    /**
     * Factory for obtaining the appropriate BufferedReader and PrintStream for stream the file.
     */
    private final ReadAndWriteStreamAbstractFactory readAndWriteStreamFactory;


    /**
     * Constructs a FileTransformerExecutor with the given list of {@link TransformerOperation} and {@link ReadAndWriteStreamAbstractFactory}.
     *
     * @param transformerOperations     List of {@link TransformerOperation} that can be applied to the input file.
     * @param readAndWriteStreamFactory Factory for obtaining the appropriate {@link BufferedReader} and {@link PrintStream} for stream the file.
     */
    public FileTransformerExecutor(List<TransformerOperation> transformerOperations, ReadAndWriteStreamAbstractFactory readAndWriteStreamFactory) {

        this.transformerOperations = transformerOperations;
        this.readAndWriteStreamFactory = readAndWriteStreamFactory;
    }


    /**
     * Executes the pipeline of TransformerOperations on the input file specified in the TransformersExecutorOptions object.
     *
     * @param options The {@link TransformersExecutorOptions} object containing the input and output files, and the operations to be executed.
     * @throws IOException if an I/O error occurs.
     */
    public void execute(TransformersExecutorOptions options) throws IOException {

        var transformers = findTransformer(options.operations(), options.inputType());
        var transformer = createTransformerPipeline(transformers);
        pipe(options.input(), options.output(), transformer);
    }


    /**
     * Applies the given UnaryOperator to each line of the input file, and writes the transformed lines to the output file.
     *
     * @param input    The input file.
     * @param output   The output file.
     * @param function The UnaryOperator to apply to each line of the input file.
     * @throws IOException if an I/O error occurs.
     */
    private void pipe(File input, File output, UnaryOperator<String> function) throws IOException {
        try (BufferedReader reader = readAndWriteStreamFactory.getStreamReader(input)) {
            try (PrintStream writer = readAndWriteStreamFactory.getStreamWriter(output)) {
                reader.lines().map(function).forEach(writer::println);
            }
        }
    }

    /**
     * High order function that creates a pipeline of TransformerOperations by applying each {@link TransformerOperation} to the output of the previous operation.
     *
     * @param transformerOperations The list of {@link TransformerOperation} to be applied.
     * @return a function in form of {@link UnaryOperator<String>} that represents the pipeline
     */
    private UnaryOperator<String> createTransformerPipeline(List<TransformerOperation> transformerOperations) {
        return line -> {
            String partialResult = line;
            for (var transformerOperation : transformerOperations) {
                partialResult = transformerOperation.transform(partialResult);
            }

            return partialResult;
        };
    }

    /**
     * Filters {@link #transformerOperations } based on {@link List<Operation>} and {@link InputType}  provided,
     * by matching the operations and supported types with those defined in the list of transformer operations passed to the
     * constructor of this class.
     *
     * @param operations lists of operations that transformers must match
     * @param type inputType that transformers must match
     * @return the filtered list of transformer operations.
     */
    private List<TransformerOperation> findTransformer(
            List<Operation> operations,
            InputType type
    ) {
        return operations
                .stream()
                .map(operation -> findTransformer(operation, type))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

}


    /**
     * Find a transformer from  {@link #transformerOperations } given {@link Operation} and {@link InputType} provided,
     *
     * @param operation operation that transformers must match
     * @param type inputType that transfomers must match
     * @return the transformer if it matches with the criteria in form of {@link Optional<TransformerOperation>>}
     */
    private Optional<TransformerOperation> findTransformer(Operation operation, InputType type){
        return transformerOperations
                .stream()
                .filter(operator -> operator.getOperation().equals(operation))
                .filter(operator -> operator.supportedTypes().contains(type))
                .findFirst();
    }

}