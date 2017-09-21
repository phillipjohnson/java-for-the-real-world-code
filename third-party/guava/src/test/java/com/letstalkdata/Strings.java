package com.letstalkdata;

import com.google.common.base.CaseFormat;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

public class Strings {

    @Test
    public void splitter() {
        Iterable<String> it = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,   qux");

        List<String> strings = Lists.newArrayList(it);

        assert 3 == strings.size();
        assert "foo".equals(strings.get(0));
        assert "bar".equals(strings.get(1));
        assert "qux".equals(strings.get(2));
    }

    @Test
    public void caseFormat() {
        assert "hello-world".equals(CaseFormat.UPPER_UNDERSCORE
                .to(CaseFormat.LOWER_HYPHEN, "HELLO_WORLD"));
    }
}
