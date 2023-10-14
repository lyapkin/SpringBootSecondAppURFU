package ru.liapkin.springbootsecondappurfu.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Codes {

    SUCCESS("success"),
    FAILED("failed");

    private final String name;

    Codes(String name) {
        this.name = name;
    }

    @JsonValue
    public String getNAme() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
