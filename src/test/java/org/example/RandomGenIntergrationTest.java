package org.example;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class RandomGenIntergrationTest {

    int[] randomNums =      {-1,    0,    1,     2,    3};
    float[] probabilities = {0.01f, 0.3f, 0.58f, 0.1f,  0.01f};
    ValidationHelper validationHelper = mock(ValidationHelper.class);
    RandomGen underTest = new RandomGen(randomNums, probabilities, new RandomFloatGen(), validationHelper);

    @Test
    public void testingWithLargeList() {
        int iteration = 100000;
        float uncertainty = 0.05f;
        Map<Integer, Integer> map = loopThroughRandomGenFor(iteration);

        for (int i = 0; i < randomNums.length; i++) {
            if (null == map.get(randomNums[i])) {
                map.put(randomNums[i], 0);
            }
            float probabilityOfNumber = map.get(randomNums[i]).floatValue() / iteration;
            assertTrue(probabilityOfNumber > probabilities[i] - uncertainty || probabilityOfNumber == 0);
            assertTrue(probabilityOfNumber < probabilities[i] + uncertainty);
        }
    }

    private  Map<Integer, Integer> loopThroughRandomGenFor(int iteration) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < iteration; i++) {
            int nextNum = underTest.nextNum();
            if(!map.containsKey(nextNum)){
                map.put(nextNum, 1);
            } else {
                Integer count = map.get(nextNum);
                map.put(nextNum, count + 1);
            }
        }
        return map;
    }

}