package com.example.tauno.calcapp;

import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Tauno on 8.03.2016.
 */
public class Calculator {
    private static ArrayList<String> memory;


    public static void addToProgress(Button btn){
        memory.add(btn.getText().toString());

}
    public static double calculateAnswer(){
        return 0;
    }

    public static boolean notEqualChar(Button btn){
        if(btn.getText().equals("/") || btn.getText().equals("*") || btn.getText().equals("+") ||
                btn.getText().equals("-") || btn.getText().equals("C") || btn.getText().equals("=")){
            return false;
        }return true;
    }
}
