package com.dujebuljat.zadatak_1;

public class PowerToCalculate implements Calculate {
    @Override
    public double calculate(double firstNumber, double secondNumber) {
        return Math.pow(firstNumber, secondNumber);
    }
}
