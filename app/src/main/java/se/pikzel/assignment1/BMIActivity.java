package se.pikzel.assignment1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Activity solving exercise 2.
 * @author Pontus Palmenäs
 */
public class BMIActivity extends ExerciseActivity {
    private EditText editLength;
    private EditText editWeight;
    private TextView textBmiValue;
    private TextView textBmiCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        editLength = (EditText) findViewById(R.id.bmiEditLength);
        editWeight = (EditText) findViewById(R.id.bmiEditWeight);
        textBmiValue = (TextView) findViewById(R.id.bmiValue);
        textBmiCategory = (TextView) findViewById(R.id.bmiCategory);
    }

    /**
     * Handle calculate and reset button clicks.
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBmiReset:
                clearTextView(editLength, editWeight, textBmiValue, textBmiCategory);
                break;
            case R.id.btnBmiCalculate:
                onClickCalculate();
                break;
        }
    }

    /**
     * Handle click on Calculate. Validates input and calculates BMI.
     */
    private void onClickCalculate() {
        try {
            float bmi = getBMI();
            textBmiValue.setText(new DecimalFormat("#.##").format(bmi));
            textBmiCategory.setText(getBmiCategory(bmi));
        } catch (InvalidInputException e) {
            showMessage("Error", e.getMessage());
        }
    }

    private void clearTextView(TextView... views) {
        for (TextView view : views) {
            view.setText("");
        }
    }

    /**
     * Get the BMI category according to the WHO BMI classification.
     */
    private String getBmiCategory(float bmi) {
        String category = "Very severely underweight";
        if (bmi >= 15 && bmi < 16) {
            category = "Severely underweight";
        } else if (bmi >= 16 && bmi < 18.5) {
            category = "Underweight";
        } else if (bmi >= 18.5 && bmi < 25) {
            category = "Normal (healthy weight)";
        } else if (bmi >= 25 && bmi < 30) {
            category = "Overweight";
        } else if (bmi >= 30 && bmi < 35) {
            category = "Obese Class I (Moderately obese)";
        } else if (bmi >= 35 && bmi < 40) {
            category = "Obese Class II (Severely obese)";
        } else if (bmi > 40) {
            category = "Obese Class III (Very severely obese)";
        }
        return category;
    }

    /**
     * Calculate BMI from input values of length and weight.
     * @return Body mass index (BMI)
     */
    private float getBMI() throws InvalidInputException {
        int length, weight;
        try {
            checkInputFields();
            length = Integer.parseInt(editLength.getText().toString());
            weight = Integer.parseInt(editWeight.getText().toString());
            checkInputBoundaries(length, weight);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please check your input.");
        }
        return calculateBmi(length, weight);
    }

    private void checkInputFields() throws InvalidInputException {
        if (editLength.length() == 0) {
            throw new InvalidInputException("Please input length.");
        } else if (editWeight.length() == 0) {
            throw new InvalidInputException("Please input weight.");
        }
    }

    private void checkInputBoundaries(int length, int weight) throws InvalidInputException {
        if (length < 50 || length > 250 || weight < 25 || weight > 350) {
            throw new InvalidInputException("Abnormal length/weight. Please advise.");
        }
    }

    /**
     * Calculate the body mass index (BMI) using standard Quetelet formula: BMI = mass(kg) / height(m)^2.
     * Takes length in cm for convenience and converts to meters.
     * @param length Length in cm
     * @param weight Weight in kg
     * @return BMI
     */
    private float calculateBmi(int length, int weight) {
        float lengthInMeters = length / 100.0f;
        return (float)weight/(lengthInMeters*lengthInMeters);
    }


}
