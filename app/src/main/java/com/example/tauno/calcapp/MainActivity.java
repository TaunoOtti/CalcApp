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

    private static final String TAG = "MainActivity";
    private static ArrayList<Double> nrMem;
    private static String strDouble = "";
    private TextView textViewShow;

    private Calculator calc = new Calculator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onCreateCalled");
        }
        setContentView(R.layout.activity_main);
        textViewShow = (TextView) findViewById(R.id.textViewShowNr);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        } else if (!calc.operatorCheck(btn)) {
            checkForCommas(btn);
        } else if (calc.operatorCheck(btn)) {
            /*if(strDouble.equals("")){
                calc.addOperator(btn);
            }*/
            if (!strDouble.equals("")) {

                calc.addNumber(strDouble);
                calc.addOperator(btn);
                strDouble = "";
                showContent();
            } else {
                /*calc.addNumber(strDouble);
                calc.addOperator(btn);
                strDouble = "";
                showContent();*/
            }

        }
        //for debug
        ArrayList<String> t = calc.getMem();
        String s = Integer.toString(t.size());
        for (int i = 0; i < t.size(); i++) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, t.get(i));

            }
        }
        Log.d(TAG, s);
    }


    public void showContent() {

        textViewShow.setText(strDouble);
    }

    public void calculateAnswer() {
        calc.addNumber(strDouble);
        strDouble = "";
        strDouble = calc.calculate();
        showContent();
    }

    public void resetAnswer() {
        strDouble = "";
        calc.reset();
        showContent();
    }

    public void checkForCommas(Button btn) {
        if (btn.getText().toString().contains(".") && strDouble.contains(".")) {

        } else {
            strDouble = strDouble + btn.getText();
            showContent();
        }
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
