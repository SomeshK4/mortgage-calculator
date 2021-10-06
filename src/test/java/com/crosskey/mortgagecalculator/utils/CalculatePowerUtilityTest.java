package com.crosskey.mortgagecalculator.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatePowerUtilityTest {

    @Test
    @DisplayName("Should return power of number")
    void shouldReturnPowerOfNumber() {
        //Given
        double number = 3;
        int power = 2;

        //When
        double result = CalculatePowerUtility.calculatePowerOfNumber(3, 2);

        //Then
        assertThat(result).isEqualTo(9);
    }
}
