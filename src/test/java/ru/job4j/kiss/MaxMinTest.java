package ru.job4j.kiss;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MaxMinTest {
    private static List<Integer> listOfIntegers;
    private static Comparator<Integer> comparator;
    private static MaxMin maxMin;
    private static int minNumberInListOfIntegers = 1;
    private static int maxNumberInListOfIntegers = 6;

    @BeforeClass
    public static void setUp() {
        listOfIntegers = Arrays.asList(1, 2, 3, 4, 5, 6);
        comparator = Comparator.comparingInt(Integer::intValue);
        maxMin = new MaxMin();
    }

    @Test
    public void whenMinThenMinNumberInListOfIntegers() {
        assertThat(maxMin.min(listOfIntegers, comparator), is(minNumberInListOfIntegers));
    }

    @Test
    public void whenMaxThenMaxNumberInListOfIntegers() {
        assertThat(maxMin.max(listOfIntegers, comparator), is(maxNumberInListOfIntegers));
    }
}