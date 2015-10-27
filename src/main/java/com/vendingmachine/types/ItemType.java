package com.vendingmachine.types;

public enum ItemType {
    A("0.65"),
    B("1.00"),
    C("1.50");

    private String text;

    ItemType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static ItemType fromString(String text) {
        if (text != null) {
            for (ItemType b : ItemType.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return null;
    }
}
