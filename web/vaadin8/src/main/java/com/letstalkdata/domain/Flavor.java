package com.letstalkdata.domain;

import java.util.Locale;

public enum Flavor {
    VANILLA, CHOCOLATE, STRAWBERRY;

    public String toString() {
        return name().charAt(0) +
                name().substring(1).toLowerCase(Locale.getDefault());
    }
}
