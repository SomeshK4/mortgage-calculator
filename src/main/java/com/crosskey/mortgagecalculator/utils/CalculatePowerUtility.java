package com.crosskey.mortgagecalculator.utils;

public final class CalculatePowerUtility {

    public CalculatePowerUtility() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static double calculatePowerOfNumber(double base, int power) {
        double result = 1;
        while (power != 0) {
            result = result * base;
            power --;
        }
        return result;
    }
}
