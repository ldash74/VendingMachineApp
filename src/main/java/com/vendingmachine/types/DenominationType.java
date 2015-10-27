package com.vendingmachine.types;

public enum DenominationType {
    NICKEL("0.05"),
    DIME("0.10"),
    QUARTER("0.25"),
    DOLLAR("1.00");

    private String text;

    DenominationType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static DenominationType fromString(String text) {
        if (text != null) {
            for (DenominationType b : DenominationType.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return null;
    }
}
