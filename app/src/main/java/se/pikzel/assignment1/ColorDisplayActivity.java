package se.pikzel.assignment1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;


public class ColorDisplayActivity extends ExerciseActivity {
    private EditText editRed;
    private EditText editGreen;
    private EditText editBlue;
    private RelativeLayout canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_display);

        editRed = (EditText) findViewById(R.id.editRed);
        editGreen = (EditText) findViewById(R.id.editGreen);
        editBlue = (EditText) findViewById(R.id.editBlue);
        canvas = (RelativeLayout) findViewById(R.id.canvas);
    }

    /**
     * Handle click on Display Color, check inputs and set the background color.
     */
    public void onClick(View view) {
        int r, g, b;
        try {
            r = Integer.parseInt(editRed.getText().toString());
            g = Integer.parseInt(editGreen.getText().toString());
            b = Integer.parseInt(editBlue.getText().toString());
        } catch (NumberFormatException e) {
            showMessage("Error", "Please enter RGB colors.");
            return;
        }
        if (isInValidRgbRange(r, g, b)) {
            canvas.setBackgroundColor(Color.rgb(r, g, b));
        } else {
            showMessage("Error", "Numbers should be 0-255.");
        }
    }

    private boolean isInValidRgbRange(int... v) {
        for (int i : v) {
            if (i <0 || i > 255) return false;
        }
        return true;
    }
}
