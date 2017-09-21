package com.letstalkdata;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class LangUtils {

    @Test
    public void CharUtils() {
        assert CharUtils.isAscii('a');
        assert !CharUtils.isAscii('\uD83D');
    }

    @Test
    public void StringUtils() {
        //null-safe operations

        assert !StringUtils.endsWith(null, "foo");
        assert null == StringUtils.reverse(null);

        // Other handy things

        assert StringUtils.isEmpty(null);
        assert StringUtils.isEmpty("");

        assert StringUtils.isNumeric("123");

        assert "00123".equals(StringUtils.leftPad("123", 5, '0'));

        assert "Hello, World!"
                .equals(StringUtils.normalizeSpace(" Hello,   World!   "));

        assert "Hello".equals(StringUtils.capitalize("hello"));
    }

    @Test
    public void randomUtils() {
        int random = RandomUtils.nextInt(5, 10);
        assert random >= 5 && random < 10;
    }

    @Test
    public void classUtils() {
        assert "java.lang".equals(ClassUtils.getPackageName(String.class));
        assert "String".equals(ClassUtils.getSimpleName(String.class));
    }
}
