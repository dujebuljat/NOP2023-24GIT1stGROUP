package com.dujebuljat.zadatak_1.data_save_load;

import java.io.Serializable;

public class CalcData implements Serializable {

    private double firstNumber;
    private double secondNumber;
    private String calculation;
    private String result;

    public CalcData(double firstNumber, double secondNumber, String calculation, String result) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.calculation = calculation;
        this.result = result;
    }

    public CalcData() {
    }

    public double getFirstNumber() {
        return firstNumber;
    }

    public double getSecondNumber() {
        return secondNumber;
    }

    public String getCalculation() {
        return calculation;
    }

    public String getResult() {
        return result;
    }

    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public void setSecondNumber(double secondNumber) {
        this.secondNumber = secondNumber;
    }

    public void setCalculation(String calculation) {
        this.calculation = calculation;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CalcData{" + "firstNumber=" + firstNumber +
                ", secondNumber=" + secondNumber +
                ", calculation=" + calculation +
                ", result=" + result +
                '}' + "\n==================================================================================================\n";
    }
}
