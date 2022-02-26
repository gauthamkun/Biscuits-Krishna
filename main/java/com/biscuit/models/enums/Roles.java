package com.biscuit.models.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Roles {

    DEVELOPER(0), PRODUCT_OWNER(1), STAKEHOLDER(2);

    private final int value;
    public static List<String> values = new ArrayList<>(
            Arrays.asList("developer", "product_owner"));


    private Roles(int value) {
        this.value = value;
    }


    public int getValue() {
        return value;
    }
}