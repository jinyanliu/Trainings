package se.sugarest.jane.trainings;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import se.sugarest.jane.trainings.data.TrainingContract.TrainingEntry;
import se.sugarest.jane.trainings.data.TrainingDbHelper;

public class MainActivity extends AppCompatActivity {

    /**
     * Database helper that will provide us access to the database
     */
    private TrainingDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To access the databse, instantiate the subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new TrainingDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information inthe onscreen TextView about state
     * of the trainings database.
     */
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                TrainingEntry._ID,
                TrainingEntry.COLUMN_TRAINING_DAY_OF_WEEK,
                TrainingEntry.COLUMN_TRAINING_TRAINING,
                TrainingEntry.COLUMN_TRAINING_TIME};

        // Perform a query on the trainings table
        Cursor cursor = db.query(
                TrainingEntry.TABLE_NAME, // The table to query
                projection, // The columns to return
                null, // The columns for the WHERE clause
                null, // The values for the WHERE clause
                null, // Don't group the rows
                null, // Don't filter by row groups
                null); // The sort order

        TextView displayView = (TextView) findViewById(R.id.text_view_trainings_list);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The trainings table contains <number of rows in Cursor> trainings.
            // _id - day of week - training - time
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The trainings table contains " + cursor.getCount() + " trainings.\n\n");
            displayView.append(TrainingEntry._ID + " - " +
                    TrainingEntry.COLUMN_TRAINING_DAY_OF_WEEK + " - " +
                    TrainingEntry.COLUMN_TRAINING_TRAINING + " - " +
                    TrainingEntry.COLUMN_TRAINING_TIME + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(TrainingEntry._ID);
            int dayColumnIndex = cursor.getColumnIndex(TrainingEntry.COLUMN_TRAINING_DAY_OF_WEEK);
            int trainingColumnIndex = cursor.getColumnIndex(TrainingEntry.COLUMN_TRAINING_TRAINING);
            int timeColumnIndex = cursor.getColumnIndex(TrainingEntry.COLUMN_TRAINING_TIME);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentDay = cursor.getString(dayColumnIndex);
                String currentTraining = cursor.getString(trainingColumnIndex);
                int currentTime = cursor.getInt(timeColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append("\n" + currentID + " - " +
                        currentDay + " - " +
                        currentTraining + " - " +
                        currentTime);
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Helper method to insert hardcoded training data into the database. For debugging purpose only.
     */
    private void insertDummyTraining() {

        // Gets the database in write mode.
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Monday's training attributes are the values.
        ContentValues values = new ContentValues();
        values.put(TrainingEntry.COLUMN_TRAINING_DAY_OF_WEEK, "Monday");
        values.put(TrainingEntry.COLUMN_TRAINING_TRAINING, "BodyCombat");
        values.put(TrainingEntry.COLUMN_TRAINING_TIME, 100);

        // Insert a new row for Monday's training in the database, returning the ID of that new row.
        // The first argument for db.insertDummyTraining() is the trainings table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Monday's training.
        long newRowId = db.insert(TrainingEntry.TABLE_NAME, null, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                //
                return true;
            case R.id.action_insert_dummy_data:
                insertDummyTraining();
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
