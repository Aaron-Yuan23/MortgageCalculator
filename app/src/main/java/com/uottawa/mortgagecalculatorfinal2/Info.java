package com.uottawa.mortgagecalculatorfinal2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Info extends AppCompatActivity {
    private double interest;
    private TextView showInterest;
    private TextView currencySign;
    private Button firstPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        currencySign = findViewById(R.id.textView18);
        currencySign.setText(getIntent().getStringExtra("sign"));

        showInterest = (TextView) findViewById(R.id.textView19);
        showInterest.setText(getIntent().getStringExtra("interest"));

        firstPage = findViewById(R.id.buttonFirstPage);
        firstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Info.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
