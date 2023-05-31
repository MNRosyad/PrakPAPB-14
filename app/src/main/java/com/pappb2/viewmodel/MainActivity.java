package com.pappb2.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private EditText edtLength, edtWidth, edtHeight;
    private Button btnCalculate;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        edtHeight = findViewById(R.id.inputHeight);
        edtWidth = findViewById(R.id.inputWidth);
        edtLength = findViewById(R.id.inputLength);
        btnCalculate = findViewById(R.id.btn_calculate);
        txtResult = findViewById(R.id.textResult);

        btnCalculate.setOnClickListener(view -> {
            String length = edtLength.getText().toString();
            String height = edtHeight.getText().toString();
            String width = edtWidth.getText().toString();

            if (width.isEmpty()) {
                edtWidth.setError("Please Enter the Width");
            } else if (height.isEmpty()) {
                edtHeight.setError("Please Enter the Height");
            } else if (length.isEmpty()) {
                edtLength.setError("Please Enter the Length");
            } else {
                viewModel.calculate(width, height, length);
            }
        });
        displayResult();
    }

    private void displayResult() {
        viewModel.result.observe(this, integer -> txtResult.setText(String.valueOf(integer)));
    }

    private void calculate(String length, String height, String width) {
        String result = String.valueOf(Integer.parseInt(width)
                * Integer.parseInt(height)
                * Integer.parseInt(length));
        txtResult.setText(result);
    }
}