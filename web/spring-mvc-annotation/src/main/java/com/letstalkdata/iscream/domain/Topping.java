package com.letstalkdata.iscream.domain;

import java.util.Locale;

public enum Topping {
    CARAMEL,
    CHERRY,
    PEANUTS,
    SPRINKLES;

    public String toString() {
        return name().charAt(0) +
                name().substring(1).toLowerCase(Locale.getDefault());
    }
}
