package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class ValidationHelperTest {

    ValidationHelper underTest  = new ValidationHelper();

    RandomFloatGen randomFloatGen = mock(RandomFloatGen.class);
    float[] probabilities = {0.1f, 0.3f, 0.5f, 0.1f};

    @Test
    public void haveToBeSameLenght() {
        assertThrows(IllegalArgumentException.class, () -> underTest.Validation(new int[1], probabilities, randomFloatGen));
    }

    @Test
    public void arrayOfNumsCantBeNull() {
        assertThrows(IllegalArgumentException.class, () -> underTest.Validation(null, probabilities, randomFloatGen));
    }

    @Test
    public void ArrayOfProbabilityCantBeNull() {
        assertThrows(IllegalArgumentException.class, () -> underTest.Validation(new int[1], null, randomFloatGen));
    }

    @Test
    public void probabilityHasToAddToOne() {
        float[] probabilities = {0.1f, 0.3f, 0.5f, 0.2f};
        assertThrows(IllegalArgumentException.class, () -> underTest.Validation(new int[1], probabilities, randomFloatGen));
    }
    @Test
    public void randomGenCantBeNull() {
        assertThrows(IllegalArgumentException.class, () -> underTest.Validation(new int[4], probabilities, null));
    }

    @Test
    public void noExceptionThrown() {
        assertDoesNotThrow(() -> underTest.Validation(new int[4], probabilities, randomFloatGen));
    }

}