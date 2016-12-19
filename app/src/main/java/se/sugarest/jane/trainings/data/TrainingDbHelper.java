package se.sugarest.jane.trainings.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import se.sugarest.jane.trainings.data.TrainingContract.TrainingEntry;

/**
 * Created by jane on 12/19/16.
 */

/**
 * Database helper for Trainings app. Manages database creation and version management.
 */
public class TrainingDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = TrainingDbHelper.class.getSimpleName();

    /**
     * Name of the database file.
     */
    private static final String DATABASE_NAME = "class.db";

    /**
     * Database version. If changing the database schema, must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@Link TrainingDbHelper}
     *
     * @param context of the app
     */
    public TrainingDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table.
        String SQL_CREATE_TRAINING_TABLE = "CREATE TABLE " + TrainingEntry.TABLE_NAME + "("
                + TrainingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TrainingEntry.COLUMN_TRAINING_DAY_OF_WEEK + " TEXT NOT NULL, "
                + TrainingEntry.COLUMN_TRAINING_TRAINING + " TEXT, "
                + TrainingEntry.COLUMN_TRAINING_TIME + " INTEGER NOT NULL DEFAULT 30);";

        //Execute the SQL statement
        db.execSQL(SQL_CREATE_TRAINING_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to be done here.
    }
}
