package com.ikhokha.techcheck.factory;

public class HasSpam implements Task {
    @Override
    public boolean Contains(String value) {
        return value.toLowerCase().contains("http") || value.toLowerCase().contains("https");
    }
}
