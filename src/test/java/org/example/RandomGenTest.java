package org.example;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RandomGenTest {

    int[] randomNums =      {-1,    0,    1,     2,    3};
    float[] probabilities = {0.01f, 0.3f, 0.58f, 0.1f,  0.01f};
    RandomFloatGen randomFloatGen = mock(RandomFloatGen.class);
    RandomGen underTest = new RandomGen(randomNums, probabilities, randomFloatGen);

    @Test
    void shouldReturnMinusOne() {
        when(randomFloatGen.nextFloat()).thenReturn(0.009f);

        int result = underTest.nextNum();

        assertEquals(-1, result);
    }

    @Test
    void shouldReturnZero() {
        when(randomFloatGen.nextFloat()).thenReturn(0.09f);

        int result = underTest.nextNum();

        assertEquals(0, result);
    }


    @Test
    void shouldReturnOne() {
        when(randomFloatGen.nextFloat()).thenReturn(0.311f);

        int result = underTest.nextNum();

        assertEquals(1, result);
    }

    @Test
    void shouldReturnTwo() {
        when(randomFloatGen.nextFloat()).thenReturn(0.91f);

        int result = underTest.nextNum();

        assertEquals(2, result);
    }

    @Test
    void shouldReturnThree() {
        when(randomFloatGen.nextFloat()).thenReturn(0.999999999f);

        int result = underTest.nextNum();

        assertEquals(3, result);
    }
    @Test
    void testNextNum() {
        when(randomFloatGen.nextFloat()).thenReturn(0.60779625f, 0.9318236f, 0.92072386f, 0.83241814f, 0.881017f,
                0.3097881f, 0.7781256f, 0.29991102f, 0.25514543f, 0.3972559f, 0.0042955f, 0.99999f);

        // map for counting the results
        Map<Integer, Integer> resultMap = new HashMap<>();
        for(int i=0; i < 12; i++){
            int nextNum = underTest.nextNum();
            if(resultMap.get(nextNum) == null){
                resultMap.put(nextNum, 1);
            } else {
                resultMap.put(nextNum, resultMap.get(nextNum) + 1);
            }
        }


        assertEquals(3, resultMap.get(0));
        assertEquals(5, resultMap.get(1));
        assertEquals(2, resultMap.get(2));
        assertEquals(1, resultMap.get(-1));
        assertEquals(1, resultMap.get(3));
    }
    @Test
    public void haveToBeSameLength() {
        RandomGen underTest = new RandomGen(new int[1], probabilities, randomFloatGen);
        assertThrows(IllegalArgumentException.class, underTest::nextNum);
        verifyNoInteractions(randomFloatGen);
    }

    @Test
    public void arrayOfNumsCantBeNull() {
        RandomGen underTest = new RandomGen(null, probabilities, randomFloatGen);
        assertThrows(IllegalArgumentException.class, underTest::nextNum);
        verifyNoInteractions(randomFloatGen);
    }

    @Test
    public void ArrayOfProbabilityCantBeNull() {
        RandomGen underTest = new RandomGen(new int[1], null, randomFloatGen);
        assertThrows(IllegalArgumentException.class, underTest::nextNum);
        verifyNoInteractions(randomFloatGen);
    }

    @Test
    public void probabilityHasToAddToOne() {
        float[] probabilities = {0.1f, 0.3f, 0.5f, 0.2f};
        RandomGen underTest = new RandomGen(new int[1], probabilities, randomFloatGen);
        assertThrows(IllegalArgumentException.class, underTest::nextNum);
        verifyNoInteractions(randomFloatGen);
    }

    @Test
    public void randomGenCantBeNull() {
        RandomGen underTest = new RandomGen(new int[4], probabilities, null);
        assertThrows(IllegalArgumentException.class, underTest::nextNum);
        verifyNoInteractions(randomFloatGen);
    }

    @Test
    public void noExceptionThrown() {
        assertDoesNotThrow(() ->  underTest.nextNum());
        verify(randomFloatGen).nextFloat();
    }


}