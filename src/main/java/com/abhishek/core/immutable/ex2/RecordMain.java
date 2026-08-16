package com.abhishek.core.immutable.ex2;

import java.util.ArrayList;

public class RecordMain {

    public static void main(String[] args) {

        ArrayList<String> tokens = new ArrayList<>();
        tokens.add("active");

        Record record = new Record(1, "value", tokens);
        System.out.println(record);   //Record{id=1, name='value', tokens=[active]}

        record.getTokens().add("new token");
        tokens.add("active1");

        System.out.println(record);   //Record{id=1, name='value', tokens=[active]}

    }
}