package com.falyrion.gymtonicapp.BMI;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatToggleButton;

import com.falyrion.gymtonicapp.R;

public class BMIActivity extends AppCompatActivity {

    SeekBar seekBar;
    EditText age, weight;
    EditText height;
    AppCompatButton button;
    androidx.appcompat.widget.Toolbar toolbar;

    // 添加对ToggleButton的引用
    AppCompatToggleButton maleToggle, femaleToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.bacharrow);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        seekBar = findViewById(R.id.seekBar);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        button = findViewById(R.id.calc);

        // 初始化ToggleButtons
        maleToggle = findViewById(R.id.male);
        femaleToggle = findViewById(R.id.female);

        maleToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maleToggle.isChecked()) {
                    femaleToggle.setChecked(false);
                }
            }
        });

        femaleToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (femaleToggle.isChecked()) {
                    maleToggle.setChecked(false);
                }
            }
        });

        Toast.makeText(getApplicationContext(), "Fill The Given Details", Toast.LENGTH_LONG).show();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            seekBar.setMax(220);
            seekBar.setMin(100);
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                height.setText(i + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bmical();
            }
        });
    }

    private void bmical() {
        float bmires = 0;
        String weights = weight.getText().toString();
        String heights = height.getText().toString();
        String ages = age.getText().toString();

        float h = Float.parseFloat(heights);
        float w = Float.parseFloat(weights);
        float a = Float.parseFloat(ages);

        float hh = h * h;
        bmires = w / (hh / 10000);


        Intent intent = new Intent(getApplicationContext(), BMIResultsActivity.class);
        intent.putExtra("bmi", bmires + "");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}


