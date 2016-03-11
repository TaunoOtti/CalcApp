package com.example.tauno.calcapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String STATE_NUMBERS = "Numbers in memory";
    private static final String STATE_FLAG = "Boolean";
    private static final String STATE_SAVEDVALUE = "LastAnswer";
    private static final String STATE_OPERATOR = "Operator";
    private static final String STATE_CURRENTNUMBER = "CurrentNr";

    private static final String TAG = "MainActivity";
    private static String strDouble = "";
    private TextView textViewShow;
    private int orient;

    private CalculatorEngine c = new CalculatorEngine();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        orient = getScreenOrientation();
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onCreateCalled");
        }
        setContentView(R.layout.activity_main);
        textViewShow = (TextView) findViewById(R.id.textViewShowNr);
        textViewShow.setText(strDouble);

        if (savedInstanceState != null) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "Restoring state");
            }

            c.setNumbers(savedInstanceState.getStringArray(STATE_NUMBERS));
            c.setOp(savedInstanceState.getChar(STATE_OPERATOR));
            c.setFlag(savedInstanceState.getBoolean(STATE_FLAG));
            c.setSavedValue(savedInstanceState.getString(STATE_SAVEDVALUE));
            strDouble = savedInstanceState.getString(STATE_CURRENTNUMBER);
            showContent();
        }

    }

    public void btnClicked(View view) {
        Button btn = (Button) view;
        String idAsString = btn.getResources().getResourceName(btn.getId());

        //DEBUG
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Button pressed :" + idAsString);
        }

        if (btn.getText().equals("C")) {
            resetAnswer();
        } else if (btn.getText().equals("=")) {
            calculateAnswer();
        } else if (!c.operatorCheck(btn)) {
            saveNumber(btn);
        } else if (c.operatorCheck(btn)) {
            setOperator(btn);
        }


        //DEBUG
        String orientation = "";
        if (orient == 1) {
            orientation = "portrait";
        } else if (orient == 2) {
            orientation = "landscape";
        } else {
            orientation = "ERROR";
        }
        if (BuildConfig.DEBUG) {
            Log.d(TAG, String.valueOf(c.forDebug()));
            Log.d(TAG, c.forDebug1());
            Log.d(TAG, c.returnSavedValue());
            Log.d(TAG, orientation);
        }

    }

    public void showContent() {
        setTextSize();
        textViewShow.setText(strDouble);
    }

    public void setOperator(Button button) {
        strDouble = "";
        if (c.checkIfTwoNumbers()) {
            calculateAnswer();
        }
        c.setOperator(button.getText().toString());
    }


    public void calculateAnswer() {
        if (c.checkIfTwoNumbers()) {
            c.calculateAns();
            strDouble = c.returnSavedValue();
            showContent();
        }

    }

    public void resetAnswer() {
        strDouble = "";
        c.reset();
        showContent();
    }

    public void saveNumber(Button btn) {

        String nr = btn.getText().toString();
        checkIfInfinity();
        if (btn.getText().toString().contains(".") && strDouble.contains(".")) {
            return;
        } else if (!c.getFlag()) {
            strDouble = strDouble + btn.getText();
            c.addNumber(nr);
            showContent();
        } else {
            c.addNumber(nr);
            strDouble = c.getSecondNr();
            showContent();
        }
    }

    public void setTextSize() {
        if (strDouble.length() > 10 && orient == 1) {
            textViewShow.setTextSize(40);
        } else if (strDouble.length() > 10 && orient == 2) {
            textViewShow.setTextSize(60);
        }
    }

    public void checkIfInfinity(){
        if(textViewShow.getText().toString().equals("Math Error")) {
            resetAnswer();
        }else {

        }
    }


    // SOURCE http://stackoverflow.com/questions/14955728/getting-orientation-of-android-device
    public int getScreenOrientation() {

        // Query what the orientation currently really is.
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return 1; // Portrait Mode

        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return 2;   // Landscape mode
        }
        return 0;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putString(STATE_SAVEDVALUE, c.returnSavedValue());
        savedInstanceState.putChar(STATE_OPERATOR, c.returnChar());
        savedInstanceState.putBoolean(STATE_FLAG, c.returnFlag());
        savedInstanceState.putStringArray(STATE_NUMBERS, c.returnNumbers());

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Saving strDouble to STATE, value is: " + c.getFirstNr());
        }
        savedInstanceState.putString(STATE_CURRENTNUMBER, c.getFirstNr());

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onStartCalled");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onResumeCalled");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onPauseCalled");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onStopCalled");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onDestroyCalled");
        }
    }


}
