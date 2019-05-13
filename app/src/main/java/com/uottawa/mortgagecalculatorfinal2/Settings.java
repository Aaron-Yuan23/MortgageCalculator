package com.uottawa.mortgagecalculatorfinal2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View;

public class Settings extends AppCompatActivity {
    private RadioButton usdButton, euroButton, poundButton, weekly, biweekly, monthly, languageButton, periodButton;
    private RadioGroup languageGroup, periodGroup;
    private Button applySetting;
    //SharedPreferences preference = getSharedPreferences("PREF",0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //retrieve ids of the RadioGroups
        languageGroup = (RadioGroup) findViewById(R.id.RadioGroup1);
        periodGroup = (RadioGroup) findViewById(R.id.RadioGroup2);
        applySetting = (Button) findViewById(R.id.button);
        //retrieve ids of the radioButtons

        usdButton = (RadioButton) findViewById(R.id.radioButton);
        euroButton = (RadioButton) findViewById(R.id.radioButton2);
        poundButton = (RadioButton) findViewById(R.id.radioButton3);
        weekly = (RadioButton) findViewById(R.id.radioButton4);
        biweekly = (RadioButton) findViewById(R.id.radioButton5);
        monthly = (RadioButton) findViewById(R.id.radioButton6);

        applySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get selected button from radioButton
                int selectedId1 = languageGroup.getCheckedRadioButtonId();
                int selectedId2 = periodGroup.getCheckedRadioButtonId();
                languageButton = (RadioButton) findViewById(selectedId1);
                periodButton = (RadioButton) findViewById(selectedId2);
                String symbol="$";
                String period="Monthly";

                if(languageButton == usdButton){
                    symbol="$";
                }

                if(languageButton == euroButton){
                    symbol="€";
                }

                if(languageButton == poundButton){
                    symbol="£";
                }

                if(periodButton == weekly){
                    period="Weekly";
                }

                if(periodButton == biweekly){
                    period = "Bi-weekly";
                }

                if(periodButton == monthly){
                    period = "Monthly";
                }
                Intent intent1 = new Intent (Settings.this, MainActivity.class);
                intent1.putExtra("Period", period);
                intent1.putExtra("Symbol", symbol);
                startActivity(intent1);
            }
        });
    }
}