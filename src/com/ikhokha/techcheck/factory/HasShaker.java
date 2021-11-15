package com.ikhokha.techcheck.factory;

public class HasShaker implements Task {

    @Override
    public boolean Contains(String value) {
        return value.toLowerCase().contains("shaker");
    }
}
