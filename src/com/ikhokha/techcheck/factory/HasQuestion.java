package com.ikhokha.techcheck.factory;

public class HasQuestion implements Task{
    @Override
    public boolean Contains(String value) {
        if(value.contains("?")){

            System.out.println(value);
            return true;
        } return false;
    }
}
