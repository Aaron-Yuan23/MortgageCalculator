package com.uottawa.mortgagecalculatorfinal2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Summary extends AppCompatActivity {
    private TextView result;
    private TextView currencySign2;
    private Button goToInfo;
    private TextView display;
    private String checkCurrency2;
    private Button goToMain;
    private String totalInterest2;
    private String sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        display = (TextView)findViewById(R.id.textView12);
        display.setText("Your"+" "+getIntent().getStringExtra("Title")+" "+"Payment is");
        result = (TextView) findViewById(R.id.textView13);
        result.setText(getIntent().getStringExtra("result"));
        currencySign2 = (TextView)findViewById(R.id.textView14);
        checkCurrency2=(getIntent().getStringExtra("SummarySymbol"));
        totalInterest2=(getIntent().getStringExtra("interest"));

        if(checkCurrency2 == null) {
            currencySign2.setText("$");
            sign="$";
        }
        else {
            currencySign2.setText((getIntent().getStringExtra("SummarySymbol")));
            sign=(getIntent().getStringExtra("SummarySymbol"));
        }
        goToMain = (Button)findViewById(R.id.button3);
        goToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Summary.this, MainActivity.class);
                startActivity(intent1);
            }
        });
        goToInfo = (Button)findViewById(R.id.button2);
        goToInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Summary.this,Info.class);
                intent.putExtra("interest", totalInterest2);
                intent.putExtra("sign", sign);
                startActivity(intent);
            }
        });
    }
}