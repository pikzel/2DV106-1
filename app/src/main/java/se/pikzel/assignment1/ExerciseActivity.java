package se.pikzel.assignment1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * @author Pontus Palmenäs
 */
public class ExerciseActivity extends Activity {

    /**
     * Show a message dialog to the user.
     */
    protected void showMessage(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();	        }
                })
                .show();
    }

    protected class InvalidInputException extends Exception {
        public InvalidInputException(String message) {
            super(message);
        }
    }
}
