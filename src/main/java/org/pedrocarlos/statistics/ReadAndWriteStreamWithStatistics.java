package org.pedrocarlos.statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.pedrocarlos.core.ReadAndWriteStreamAbstractFactory;

public final class ReadAndWriteStreamWithStatistics implements ReadAndWriteStreamAbstractFactory {
    @Override
     public BufferedReader getStreamReader(File input) throws FileNotFoundException {
        return new BufferedReaderWithStatistics(new FileReader(input));
    }
}