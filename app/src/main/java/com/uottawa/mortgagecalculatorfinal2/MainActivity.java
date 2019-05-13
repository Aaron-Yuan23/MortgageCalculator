package com.uottawa.mortgagecalculatorfinal2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    //declare variables;
    private EditText T1;
    private EditText T2;
    private EditText T3;
    private Button calculatePayment;
    private Button goToSettings;
    private TextView currencySign1;
    private String checkCurrency;
    private double coefficient;
    private String checkPeriod;
    private String periodResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // extract values from the inputs
        T1 = (EditText)findViewById(R.id.editText1);
        T2 = (EditText)findViewById(R.id.editText2);
        T3 = (EditText)findViewById(R.id.editText3);
        calculatePayment = (Button)findViewById(R.id.button4);


        // extract data from activity settings
        currencySign1 = (TextView)findViewById((R.id.textView3));
        checkCurrency = getIntent().getStringExtra("Symbol");

        // display $ by default
        if(checkCurrency == null) {
            currencySign1.setText("$");
        }
        else {
            currencySign1.setText((getIntent().getStringExtra("Symbol")));
        }

        checkPeriod = getIntent().getStringExtra("Period");
        //obtain values of the period sent from settings
        if(checkPeriod == null) {
            coefficient = 1.0;
            periodResult = "Monthly";
        }
        else if(checkPeriod.equals("Weekly") ){
            coefficient = 0.25;
            periodResult = "Weekly";
        }
        else if(checkPeriod.equals("Bi-weekly") ){
            coefficient = 0.5;
            periodResult = "Bi-weekly";
        }
        else{
            coefficient = 1.0;
            periodResult = "Monthly";
        }

        //setting up onClick functions and calculations
        calculatePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check validity of the inputs
                if(TextUtils.isEmpty(T1.getText().toString())
                        ||!isValidCaptial(T1.getText().toString())||T1.getText().toString()=="0"){
                    //generate error message if input is invalid
                    T1.setError("Please enter a valid number");
                    return;
                }
                if(TextUtils.isEmpty(T2.getText().toString())
                        ||!isValidInterest(T2.getText().toString())||T2.getText().toString()=="0"){
                    T2.setError("Please enter a valid decimal number");
                    return;
                }
                if(TextUtils.isEmpty(T3.getText().toString())
                        ||!isValidYears(T3.getText().toString())||T3.getText().toString()=="0"){
                    T3.setError("Please enter a valid number");
                    return;
                }
                int num1 = Integer.parseInt(T1.getText().toString());
                double num2 = Double.parseDouble(T2.getText().toString());
                int num3 = Integer.parseInt(T3.getText().toString());

                //sending data to activity Summary
                double step1 = 1+((num2*0.01)/12);
                double step2 = (double) Math.pow(step1,(num3*12));
                double answer = (coefficient*num1*((num2*0.01/12)*step2))/(step2-1);
                answer = Math.round(answer*100)/100D;
                String answer2 = Double.toString(answer);

                double answerInterest = (((num1*12*num3)*((num2*0.01/12)*step2))/(step2-1))-(num1);
                answerInterest = Math.round(answerInterest*100)/100D;
                String totalInterest= Double.toString(answerInterest);
                Intent intent = new Intent(MainActivity.this, Summary.class);
                intent.putExtra("result", answer2);
                intent.putExtra("Title", periodResult);
                intent.putExtra("SummarySymbol",checkCurrency);
                intent.putExtra("interest",totalInterest);
                startActivity(intent);
            }
            public boolean isValidCaptial(String num1){
                int stringNum= Integer.parseInt(num1);
                if (stringNum<0){
                    return false;
                }
                if (stringNum<1000000){
                    return true;
                }
                else{
                    return false;
                }

            }
            public boolean isValidInterest(String num1){
                double stringNum= Double.parseDouble(num1);
                if (stringNum<0){
                    return false;
                }
                if (stringNum<15.0){
                    return true;
                }
                else{
                    return false;
                }
            }
            public boolean isValidYears(String num1){
                int stringNum= Integer.parseInt(num1);
                if (stringNum<0){
                    return false;
                }
                if (stringNum<46){
                    return true;
                }
                else{
                    return false;
                }
            }
        });
        goToSettings = (Button)findViewById(R.id.button);
        goToSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Settings.class);
                startActivity(intent);
            }
        });
    }

}
