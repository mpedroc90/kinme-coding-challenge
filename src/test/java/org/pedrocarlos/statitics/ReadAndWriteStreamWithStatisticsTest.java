package org.pedrocarlos.statitics;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReadAndWriteStreamWithStatisticsTest {


    private final ReadAndWriteStreamWithStatistics factory = new ReadAndWriteStreamWithStatistics();

    @Test
    void shouldReturnBufferedReaderWithStatiticsWhenCallGetStreamReader() throws IOException {
        var file = File.createTempFile("temp", "");
        assertTrue(factory.getStreamReader(file) instanceof BufferedReaderWithStatistics);
    }


    @Test
    void shouldReturnSytemOutWhenCallOutputWithNullValue() throws IOException {
        assertEquals(System.out, factory.getStreamWriter(null));
    }
}