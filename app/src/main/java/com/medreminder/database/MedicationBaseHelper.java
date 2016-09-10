/**
 * Created by Nelson Joseph on 26-May-2016.
 * Reg. No: 75814200014
 * Roll. No: 1406FOSS0011
 */

package com.medreminder.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MedicationBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "medication.db";

    public MedicationBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MedicationDBSchema.MedicationTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                MedicationDBSchema.MedicationTable.Cols.UUID + ", " +
                MedicationDBSchema.MedicationTable.Cols.MEDICINE_NAME + ", " +
                MedicationDBSchema.MedicationTable.Cols.DOSAGE + ", " +
                MedicationDBSchema.MedicationTable.Cols.REMINDER_TIME + ", " +
                MedicationDBSchema.MedicationTable.Cols.INSTRUCTIONS + ", " +
                MedicationDBSchema.MedicationTable.Cols.TOTAL_NO_OF_TABLETS + ", " +
                MedicationDBSchema.MedicationTable.Cols.REMINDE_ME_WHEN + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}