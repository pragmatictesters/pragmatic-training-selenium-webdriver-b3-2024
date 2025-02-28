package com.pragmatic.java.lambda;

import org.testng.annotations.Test;

import java.util.Random;
import java.util.function.IntBinaryOperator;

public class CalculatorTest {

    @Test
    public void testCalculatorLambda(){
          Calculator calculator = (int x, int y) -> {
            Random random = new Random();
            int randomNumber = random.nextInt(50);
            return randomNumber + x +y;
        };

        System.out.println(calculator.randomNumber(5,10));
    }

    @Test
    public void testCalculatorWithExistingInterface(){
        IntBinaryOperator calculator = (int x, int y) -> {
            Random random = new Random();
            int randomNumber = random.nextInt(50);
            return  randomNumber + x+ y ;
        };

        System.out.println(calculator.applyAsInt(4, 6 ));
    }
}
