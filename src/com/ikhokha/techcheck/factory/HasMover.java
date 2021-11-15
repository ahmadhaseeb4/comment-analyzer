package com.ikhokha.techcheck.factory;

public class HasMover implements Task {

    @Override
    public boolean Contains(String value) {
        return value.toLowerCase().contains("mover");
    }
}
