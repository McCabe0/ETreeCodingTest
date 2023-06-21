package org.example;

import java.util.Arrays;

public class ValidationHelper {

    public void Validation(int[] randomNums, float[] probabilities, RandomFloatGen randomGenerator) {
        if(randomNums == null){
            throw new IllegalArgumentException("randomNums not allowed to be null");
        }

        if(probabilities == null){
            throw new IllegalArgumentException("probabilities not allowed to be null");
        }

        if(randomGenerator == null){
            throw new IllegalArgumentException("randomGenerator not allowed to be null");
        }

        if(randomNums.length != probabilities.length){
            throw new IllegalArgumentException("randomNums array length with " + Arrays.toString(randomNums) + " isn't equal to probabilities array length with " + Arrays.toString(probabilities) + " !");
        }

        float total = 0;

        for(float probability : probabilities){
            total += probability;
        }

        if(total != 1){
            throw new IllegalArgumentException("The sum of all probabilities should be 1 but is " + total);
        }
    }
}
