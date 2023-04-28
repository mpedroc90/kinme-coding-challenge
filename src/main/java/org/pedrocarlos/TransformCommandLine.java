package org.pedrocarlos;

import java.io.BufferedOutputStream;
import java.io.File;
import java.util.Arrays;
import picocli.CommandLine;

@CommandLine.Command(name = "example")
public class TransformCommandLine implements Runnable {

    @CommandLine.Option(names = {"--input"}, description = "specifies a plaintext file (UTF8 encoded)", required = true)
    private File input;

    @CommandLine.Option(
            names = {"--inputtype"},
            description = "specifies the type of data elements.(string, int and double)",
            required = true
    )
    private String inputType;


    @CommandLine.Option(names = {"--operations"}, arity = "1..*" ,description = "describes a pipeline of what to do with data elements. Each operation\n" + "only applies to certain types of data. Implement at least the following operations:\n" + "• capitalize: Capitalizes a string element.\n" + "• reverse: Reverses the characters in a string, or the decimal string\n" + "representation of an integer (1020 becomes 201).\n" + "• neg: Negates an integer or double number (10 becomes -10).", required = true)
    private String[] operations;

    @CommandLine.Option(names = {"--threads"}, description = "specifies the number of threads to use when reading the input file and\n" + "applying operations.", defaultValue = "1")
    private int threads;

    @CommandLine.Option(names = {"--output"}, description = "specifies a plaintext file (UTF8 encoded)")
    private BufferedOutputStream output;

    public void run() {
        System.out.println(input.getAbsolutePath());
        System.out.println(inputType);
        Arrays.stream(operations).forEach(System.out::println);
        System.out.println(threads);
    }


}