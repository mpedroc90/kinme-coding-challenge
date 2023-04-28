package org.pedrocarlos.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

public interface ReadAndWriteStreamAbstractFactory {
    public default BufferedReader getStreamReader(File input) throws FileNotFoundException {
        return new BufferedReader(new FileReader(input));
    }

    public default PrintStream getStreamWriter(File output) throws FileNotFoundException {
        return output == null ? System.out : new PrintStream(new FileOutputStream(output));
    }

}