/**
 * Created by Nelson Joseph on 26-May-2016.
 * Reg. No: 75814200014
 * Roll. No: 1406FOSS0011
 */

package com.medreminder.database;

public class MedicationDBSchema {
    public static final class MedicationTable {
        public static final String NAME = "Medication";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String MEDICINE_NAME = "medicine_name";
            public static final String DOSAGE = "dosage";
            public static final String REMINDER_TIME = "reminder_time";
            public static final String INSTRUCTIONS = "instructions";
            public static final String TOTAL_NO_OF_TABLETS = "total_no_of_tablets";
            public static final String REMINDE_ME_WHEN = "remind_me_when";
        }
    }
}