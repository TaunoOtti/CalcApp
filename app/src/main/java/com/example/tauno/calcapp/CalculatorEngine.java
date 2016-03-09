package com.example.tauno.calcapp;

import android.widget.Button;

/**
 * Created by Tauno on 9.03.2016.
 */
public class CalculatorEngine {

    private String[] numbers = {"", ""};
    private char operator = '\u0000';
    private double savedValue = 0;
    private boolean flag = false;
    private static final int maxLength = 20;


    public void addNumber(String number) {
        if (!flag && numbers[0].length() <= maxLength ) {
            numbers[0] += number;
        } else if (flag && numbers[0].length() <= maxLength ) {
            numbers[1] += number;
        }
    }

    public void setOperator(String op) {
        if (numbers[0] != "") {
            operator = op.charAt(0);
            flag = true;
        }
    }

    public void calculateAns() {

        double a = Double.parseDouble(numbers[0]);
        double b = Double.parseDouble(numbers[1]);

        if (numbers[0] != "" && numbers[1] != "") {
            switch (operator) {
                case '+':
                    savedValue = a + b;
                    break;
                case '-':
                    savedValue = a - b;
                    break;
                case '*':
                    savedValue = a * b;
                    break;
                case '/':
                    savedValue = a / b;
                    break;
            }
            numbers[0] = String.valueOf(savedValue);
            numbers[1] = "";
        }
    }


    public boolean operatorCheck(Button btn) {
        if (btn.getText().equals("/") || btn.getText().equals("*") || btn.getText().equals("+") ||
                btn.getText().equals("-")) {
            return true;
        } else {
            return false;
        }
    }

    public void reset() {
        savedValue = 0;
        operator = '\u0000';
        numbers = new String[]{"", ""};
        flag = false;
    }

    public boolean checkIfTwoNumbers() {
        if (numbers[0] != "" && numbers[1] != "") {
            return true;
        }
        return false;
    }

    public String getSecondNr() {
        return numbers[1];
    }

    public boolean getFlag() {
        return flag;
    }


    public String returnSavedValue() {
        return String.valueOf(savedValue);
    }

    public String[] returnNumbers() {
        return numbers;
    }

    public char returnChar() {
        return operator;
    }

    public boolean returnFlag() {
        return flag;
    }

    public void setSavedValue(String value) {
        savedValue = Double.parseDouble(value);
    }

    public void setNumbers(String[] nr) {
        numbers = nr;
    }

    public void setOp(char ch) {
        operator = ch;
    }

    public void setFlag(boolean bo) {
        flag = bo;
    }


    //DEBUG
    public char forDebug() {
        return operator;
    }

    public String forDebug1() {
        return numbers[0] + " " + numbers[1];
    }

}