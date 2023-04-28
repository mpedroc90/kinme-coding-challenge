package org.pedrocarlos;

import java.util.Arrays;
import java.util.List;
import org.pedrocarlos.core.FileTransformerExecutor;
import org.pedrocarlos.statitics.Statistics;
import org.pedrocarlos.statitics.ReadAndWriteStreamWithStatistics;
import org.pedrocarlos.app.FileTransformerCommandLine;
import org.pedrocarlos.core.operations.CapitalizeTransformer;
import org.pedrocarlos.core.operations.NegativeTransformer;
import org.pedrocarlos.core.operations.ReverseNumbersTransformer;
import org.pedrocarlos.core.operations.ReverseTransformer;
import org.pedrocarlos.core.operations.TransformerOperation;
import picocli.CommandLine;

/**
 * Main class.
 *
 * @author KNIME GmbH
 */
public class Main {

    // Register Operators
    private static final List<TransformerOperation> transformOperations = Arrays.asList(
            new CapitalizeTransformer(),
            new NegativeTransformer(),
            new ReverseTransformer(),
            new ReverseNumbersTransformer()
    );

    public static void main(String[] args) {

        new CommandLine(new FileTransformerCommandLine(new FileTransformerExecutor(transformOperations, new ReadAndWriteStreamWithStatistics()))).execute(args);

//          // DO NOT CHANGE THE FOLLOWING LINES OF CODE
        System.out.printf("Processed %d lines (%d of which were unique)%n", //
                Statistics.getInstance().getNoOfLinesRead(), //
                Statistics.getInstance().getNoOfUniqueLines());
    }
}