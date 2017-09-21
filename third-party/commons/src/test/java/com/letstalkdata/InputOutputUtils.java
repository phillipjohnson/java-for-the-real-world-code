package com.letstalkdata;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.CanReadFileFilter;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Paths;

public class InputOutputUtils {

    @Test
    public void streams() throws Exception {
        final String DROM = "src/test/resources/declaration.txt";
        File declaration = Paths.get(DROM).toFile();
        InputStream is = new FileInputStream(declaration);

        char[] read = IOUtils.toCharArray(is, Charset.defaultCharset());
        is.close();

        assert new String(read).startsWith("The representatives");

        StringWriter sw = new StringWriter();
        is = new FileInputStream(declaration);
        IOUtils.copy(is, sw, Charset.defaultCharset());
        is.close();

        assert sw.toString().startsWith("The representatives");
    }

    @Test
    public void fileUtils() throws Exception {
        final String DROM = "src/test/resources/declaration.txt";
        File declaration = Paths.get(DROM).toFile();
        String s = FileUtils.readFileToString(declaration,
                Charset.defaultCharset());

        assert s.startsWith("The representatives");

        assert FileUtils.sizeOf(declaration) > 0;

        assert "declaration.txt".equals(
                FileUtils.listFiles(
                    Paths.get("src/test/resources").toFile(),
                    CanReadFileFilter.CAN_READ,
                    null
                ).iterator().next().getName());
    }
}
