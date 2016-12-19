package se.sugarest.jane.trainings.data;

import android.provider.BaseColumns;

/**
 * Created by jane on 12/17/16.
 */

public class TrainingContract {

    // An empty private constructor makes sure that the class is not going to be initialised.
    private TrainingContract() {
    }

    public static abstract class TrainingEntry implements BaseColumns {

        public final static String TABLE_NAME = "trainings";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_TRAINING_DAY_OF_WEEK = "day";
        public final static String COLUMN_TRAINING_TRAINING = "training";
        public final static String COLUMN_TRAINING_TIME = "time";

        public final static int DAY_OF_WEEK_MONDAY = 1;
        public final static int DAY_OF_WEEK_TUESDAY = 2;
        public final static int DAY_OF_WEEK_WEDNESDAY = 3;
        public final static int DAY_OF_WEEK_THURSDAY = 4;
        public final static int DAY_OF_WEEK_FRIDAY = 5;
        public final static int DAY_OF_WEEK_SATURDAY = 6;
        public final static int DAY_OF_WEEK_SUNDAY = 7;

    }
}


