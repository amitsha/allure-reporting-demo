package com.amitsh.allure;

import java.util.List;

public class Calculator {

    public int add(List<Integer> integers) {

        if (integers.size() < 2) {
            throw new IllegalArgumentException("Please provide at least two numbers.");
        }

        int total = 0;
        for (Integer integer : integers) {
            total = total + integer;
        }

        return total;
    }
}
