package com.example.ljy.c04_view;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    private Random random = new Random();

    @Test
    public void getRandomArrayTest() {
        ArrayList<Integer> result = getRandomArray();
        System.out.println(result);
        System.out.println(result.size());

        for (int i = 1; i < 26; i++) {
            if (!result.contains(i)) {
                assertFalse(true);
            }
        }
    }

    private ArrayList<Integer> getRandomArray() {
        ArrayList<Integer> result = new ArrayList<>();
        int index = 0;
        while (index < 25) {
            int randomNumber = random.nextInt(25) + 1;
            if (!result.contains(randomNumber)) {
                result.add(randomNumber);
                index++;
            }
        }
        return result;
    }
}