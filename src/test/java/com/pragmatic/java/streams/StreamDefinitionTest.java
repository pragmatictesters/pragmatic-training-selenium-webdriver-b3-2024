package com.pragmatic.java.streams;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Stream;


public class StreamDefinitionTest {

    @Test
    public void testStreamDistinct() {
        String[] products = new String[]{"Product One", "Product One", "Second Product", "New Product"};
        Stream<String> productsStream = Arrays.stream(products);
        long distinctProductCount = productsStream
                .distinct().count();
        Assert.assertEquals(distinctProductCount, 3);
    }

    @Test
    public void testStreamSorted() {
        String[] products = new String[]{"Product One", "Product One", "Second Product", "New Product"};
        Stream<String> productsStream = Arrays.stream(products);
        productsStream
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    public void testStreamMap() {
        String[] products = new String[]{"Product One", "Product One", "Second Product", "New Product"};
        Stream<String> productsStream = Arrays.stream(products);
        productsStream
                .sorted()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }


    @Test
    public void testStreamFilter() {
        String[] products = new String[]{"Product One", "Product One", "Second Product", "New Product"};
        Stream<String> productsStream = Arrays.stream(products);
        productsStream
                .sorted()
                .filter(product -> product.startsWith("Product"))
                .forEach(System.out::println);
    }


    @Test
    public void testStreamFilterMatch() {
        String[] products = new String[]{"Product One", "Product One", "Second Product", "New Product"};
        Stream<String> productsStream = Arrays.stream(products);
        productsStream
                .sorted()
                .filter(product -> product.matches("Product One"))
                .forEach(System.out::println);
    }


    @Test
    public void testSortedWithLongerStrings(){
        String[] products = new String[]{"Product One", "Product One", "Second Product", "New Product"};
        Stream<String> productsStream = Arrays.stream(products);
        productsStream
                .filter(product -> product.startsWith("P"))
                .filter(product -> product.length()>5)
                .forEach(System.out::println);
    }





}
