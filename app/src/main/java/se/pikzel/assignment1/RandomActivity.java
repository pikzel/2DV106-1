package se.pikzel.assignment1;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

/**
 * Activity solving exercise 1.
 * @author Pontus Palmenäs
 */

public class RandomActivity extends ExerciseActivity {

    private TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        label =  (TextView)findViewById(R.id.label);
    }

    public void onClickNewRandom(View view) {
        label.setText(""+new Random().nextInt(100+1));
    }
}
