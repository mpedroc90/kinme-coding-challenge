package org.pedrocarlos.core;

import java.io.File;
import java.util.List;

/**
 A record that represents options for executing {@link FileTransformerExecutor}s on a file.
 <p>
 @param input the input file to read from.
 @param inputType the input type for file content.
 @param operations the list of transformer operations to apply.
 @param threads the number of threads to use for parallel execution.
 @param output the output file to write the results to.
 */
public record TransformersExecutorOptions(
        File input,
       InputType inputType,
      List<Operation> operations,
      int threads,
      File output
) { }