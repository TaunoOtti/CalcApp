package com.example.tauno.calcapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    private static final String TAG = "MainActivity";
    private static String strDouble = "";
    private TextView textViewShow;

    private CalculatorEngine c = new CalculatorEngine();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        }
    }

    public void btnClicked(View view) {
        Button btn = (Button) view;
        String idAsString = btn.getResources().getResourceName(btn.getId());

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
        if (BuildConfig.DEBUG) {
            Log.d(TAG, String.valueOf(c.forDebug()));
            Log.d(TAG, c.forDebug1());
            Log.d(TAG, c.returnSavedValue());
        }

    }


    public void showContent() {
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
//TODO: KOMA EI TÖÖTA.
    public void saveNumber(Button btn) {

        String nr = btn.getText().toString();

        if (btn.getText().toString().contains(".") && strDouble.contains(".")) {
            return;
        } else if (!c.getFlag()) {
                strDouble = strDouble + btn.getText();
                c.addNumber(nr);
                showContent();
            } else {
                c.addNumber(nr);
                strDouble = c.getSecondNr();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(STATE_SAVEDVALUE, c.returnSavedValue());
        savedInstanceState.putChar(STATE_OPERATOR, c.returnChar());
        savedInstanceState.putBoolean(STATE_FLAG, c.returnFlag());
        savedInstanceState.putStringArray(STATE_NUMBERS, c.returnNumbers());

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
