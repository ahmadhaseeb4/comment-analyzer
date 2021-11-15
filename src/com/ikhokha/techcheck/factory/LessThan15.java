package com.ikhokha.techcheck.factory;

public class LessThan15 implements Task{
    @Override
    public boolean Contains(String value) {
        return value.length() < 15 ? true: false;
    }
}
