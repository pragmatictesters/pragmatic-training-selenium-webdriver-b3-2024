package com.pragmatic.java.lambda;

import org.testng.annotations.Test;

import java.util.function.Function;

public class GreetingTest {

    @Test
    public void testGreeting(){
        Greeting greeting = new HelloGreeting();
        greeting.greet();
    }

    @Test
    public void testGreetImplementation(){
        Greeting greeting = new Greeting() {
            @Override
            public void greet() {
                System.out.println("Hello Selenium!");
            }
        };
        greeting.greet();
    }

    @Test
    public void testLambdaImplementation(){
        Greeting greeting = ()-> System.out.println("Hello Selenium!");
        greeting.greet();
    }


    @Test
    public void testLambdaWithExistingInterface(){
        Function<String, String> greeting = name -> {
            return "Hello " + name;
        };
        System.out.println(greeting.apply("Janesh"));

    }
}
