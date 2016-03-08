package com.example.tauno.calcapp;

import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Tauno on 8.03.2016.
 */
public class Calculator {
    private ArrayList<String> numberMemory = new ArrayList<String>();
    private double sum;

    public void addNumber(String str) {
        numberMemory.add(str);
    }

    //add operator to array, if last element is opertor, it won't add any more and changes
    //it until user enter number
    public void addOperator(Button btn) {
        if (numberMemory.isEmpty()) {
            return;
        }
        if (checkIfOperatorLastElement()) {
            numberMemory.set(numberMemory.size() - 1, getString(btn));

        } else if (!checkIfOperatorLastElement()) {
            numberMemory.add(btn.getText().toString());

        }
    }

    //check, if numberMemory array last element is operator
    public boolean checkIfOperatorLastElement() {
        if (numberMemory.get(numberMemory.size() - 1).equals("/") || numberMemory.get(numberMemory.size() - 1).equals("*") ||
                numberMemory.get(numberMemory.size() - 1).equals("+") || numberMemory.get(numberMemory.size() - 1).equals("-")) {
            return true;
        } else {
            return false;
        }
    }


    public String getString(Button btn) {
        return btn.getText().toString();
    }

    public void reset() {
        numberMemory.clear();
    }

    public String calculate() {
        sum = 0;
        calculateSum();
        String doubleSum = "";
        return doubleSum.valueOf(sum);

    }

    public void calculateSum() {

        if (numberMemory.contains("/") || numberMemory.contains("*")) {
            for (int i = 0; i < numberMemory.size(); i++) {
                if (numberMemory.get(i).equals("*")) {
                    sum = Double.parseDouble(numberMemory.get(i - 1)) * Double.parseDouble(numberMemory.get(i + 1));
                    setSum(i);
                } else if (numberMemory.get(i).equals("/")) {
                    sum = Double.parseDouble(numberMemory.get(i - 1)) / Double.parseDouble(numberMemory.get(i + 1));
                    setSum(i);
                }
            }
        }

        for (int i = 0; i < numberMemory.size(); i++) {
            if (numberMemory.get(i).equals("+")) {
                sum = Double.parseDouble(numberMemory.get(i - 1)) + Double.parseDouble(numberMemory.get(i + 1));
                setSum(i);
            } else if (numberMemory.get(i).equals("-")) {
                sum = Double.parseDouble(numberMemory.get(i - 1)) - Double.parseDouble(numberMemory.get(i + 1));
                setSum(i);
            } else if (numberMemory.get(i).equals("*")) {
                sum = Double.parseDouble(numberMemory.get(i - 1)) * Double.parseDouble(numberMemory.get(i + 1));
                setSum(i);
            } else if (numberMemory.get(i).equals("/")) {
                sum = Double.parseDouble(numberMemory.get(i - 1)) / Double.parseDouble(numberMemory.get(i + 1));
                setSum(i);
            }
        }
    }


    public void setSum(int i) {
        String doubleSum = "";
        numberMemory.set(i + 1, doubleSum.valueOf(sum));
        numberMemory.remove(i);
        numberMemory.remove(i - 1);
    }

    public ArrayList<String> getMem() {
        return numberMemory;
    }

    public boolean operatorCheck(Button btn) {
        if (btn.getText().equals("/") || btn.getText().equals("*") || btn.getText().equals("+") ||
                btn.getText().equals("-")) {
            return true;
        } else {
            return false;
        }
    }
}
