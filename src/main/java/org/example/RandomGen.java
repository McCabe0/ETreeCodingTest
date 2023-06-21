package org.example;

import java.util.Arrays;
import java.util.TreeMap;


    public class RandomGen {
        // Values that may be returned by nextNum()
        private final int[] randomNums;
        // Probability of the occurrence of randomNums
        private final float[] probabilities;
        // Random generator for a float value between 0 and 1
        private final RandomFloatGen randomGenerator;
        // TreeMap for storing interval possibilities
        private TreeMap<Float, Integer> randomMap;

        public RandomGen(int[] randomNums, float[] probabilities, RandomFloatGen randomGenerator){
            this.randomNums = randomNums;
            this.probabilities = probabilities;
            this.randomGenerator = randomGenerator;
        }

        public int nextNum() {
            validation(randomNums, probabilities, randomGenerator);
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

        protected void validation(int[] randomNums, float[] probabilities, RandomFloatGen randomGenerator) {
            if (randomNums == null) {
                throw new IllegalArgumentException("randomNums not allowed to be null");
            }

            if (probabilities == null) {
                throw new IllegalArgumentException("probabilities not allowed to be null");
            }

            if (randomGenerator == null) {
                throw new IllegalArgumentException("randomGenerator not allowed to be null");
            }

            if (randomNums.length != probabilities.length) {
                throw new IllegalArgumentException("randomNums array length with " + Arrays.toString(randomNums) + " isn't equal to probabilities array length with " + Arrays.toString(probabilities) + " !");
            }

            float total = 0;

            for (float probability : probabilities) {
                total += probability;
            }

            if (total != 1) {
                throw new IllegalArgumentException("The sum of all probabilities should be 1 but is " + total);
            }
        }

}
