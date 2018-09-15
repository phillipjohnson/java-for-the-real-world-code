package com.letstalkdata;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.CanReadFileFilter;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Paths;

public class InputOutputUtils {

    @Test
    public void streams() throws Exception {
        final var DROM = "src/test/resources/declaration.txt";
        var declaration = Paths.get(DROM).toFile();
        var is = new FileInputStream(declaration);

        var read = IOUtils.toCharArray(is, Charset.defaultCharset());
        is.close();

        assert new String(read).startsWith("The representatives");

        var sw = new StringWriter();
        is = new FileInputStream(declaration);
        IOUtils.copy(is, sw, Charset.defaultCharset());
        is.close();

        assert sw.toString().startsWith("The representatives");
    }

    @Test
    public void fileUtils() throws Exception {
        final var DROM = "src/test/resources/declaration.txt";
        var declaration = Paths.get(DROM).toFile();
        var s = FileUtils.readFileToString(declaration,
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
