package org.example;

import java.util.Random;
import java.util.TreeMap;


    public class RandomGen {
        // Values that may be returned by nextNum()
        private final int[] randomNums;
        // Probability of the occurence of randomNums
        private final float[] probabilities;
        // Random generator for a float value between 0 and 1
        private final RandomFloatGen randomGenerator;
        private final ValidationHelper validationHelper;
        // TreeMap for storing interval possibilities
        private TreeMap<Float, Integer> randomMap;

        public RandomGen(int[] randomNums, float[] probabilities, RandomFloatGen randomGenerator, ValidationHelper validationHelper){
            this.randomNums = randomNums;
            this.probabilities = probabilities;
            this.randomGenerator = randomGenerator;
            this.validationHelper = validationHelper;
        }

        public int nextNum() {
            validationHelper.Validation(randomNums, probabilities, randomGenerator);
            cumulativePossibilities(randomNums, probabilities);
            float randFloat = randomGenerator.nextFloat();
            return randomMap.ceilingEntry(randFloat).getValue();
        }


        private void cumulativePossibilities(int[] randomNums, float[] probabilities) {
            randomMap = new TreeMap<>();
            float interval = 0;
            for(int i = 0; i< probabilities.length; i++) {
                interval += probabilities[i];
                randomMap.put(interval, randomNums[i]);
            }
        }

}
