package com.amitsh.allure.tests;

import com.amitsh.allure.Calculator;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertTrue;

@Features("Add numbers")
@Stories("Unit Tests")
public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();

    }

    @Test
    @Title("Adding 3,4,5 should result in a total of 12")
    public void shouldAddThreeNumbers() throws Exception {
        assertTrue(calculator.add(Arrays.asList(3, 4, 5)) == 12);
    }

    @Test
    @Title("Adding only 1 number should result in an error message")
    public void shouldThrowAnExceptionWithErrorMessageOnAddingOnly1Number() throws Exception {
        try {
            calculator.add(Collections.singletonList(3));
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("Please provide at least two numbers."));
        }
    }

}