package org.pedrocarlos.statitics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import org.pedrocarlos.core.ReadAndWriteStreamAbstractFactory;
import org.pedrocarlos.statitics.BufferedReaderWithStatistics;

public final class ReadAndWriteStreamWithStatistics implements ReadAndWriteStreamAbstractFactory {
    @Override
     public BufferedReader getStreamReader(File input) throws FileNotFoundException {
        return new BufferedReaderWithStatistics(new FileReader(input));
    }
}