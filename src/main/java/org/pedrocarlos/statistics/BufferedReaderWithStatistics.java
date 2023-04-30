package org.pedrocarlos.statistics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class BufferedReaderWithStatistics extends BufferedReader {
    public BufferedReaderWithStatistics(Reader in) {
        super(in);
    }

    @Override
    public String readLine() throws IOException {
        String line = super.readLine();
        if (line != null) {
            Statistics.getInstance().updateStatisticsWithLine(line);
        }
        return line;
    }
}