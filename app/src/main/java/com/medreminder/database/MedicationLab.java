/**
 * Created by Nelson Joseph on 26-May-2016.
 * Reg. No: 75814200014
 * Roll. No: 1406FOSS0011
 */

package com.medreminder.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.medreminder.Medication;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MedicationLab {

    private static MedicationLab sMedicationLab = null;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static MedicationLab get(Context context) {
        if (sMedicationLab == null) {
            sMedicationLab = new MedicationLab(context);
        }
        return sMedicationLab;
    }

    private MedicationLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new MedicationBaseHelper(mContext).getWritableDatabase();
    }

    private static ContentValues getContentValues(Medication medication) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MedicationDBSchema.MedicationTable.Cols.UUID, medication.getId().toString());
        contentValues.put(MedicationDBSchema.MedicationTable.Cols.MEDICINE_NAME, medication.getMedicineName().toString());
        contentValues.put(MedicationDBSchema.MedicationTable.Cols.DOSAGE, medication.getDosage().toString());
        contentValues.put(MedicationDBSchema.MedicationTable.Cols.REMINDER_TIME, medication.getReminderTime().toString());
        contentValues.put(MedicationDBSchema.MedicationTable.Cols.INSTRUCTIONS, medication.getInstructions().toString());
        contentValues.put(MedicationDBSchema.MedicationTable.Cols.TOTAL_NO_OF_TABLETS, medication.getTotalNumberOfTablets().toString());
        contentValues.put(MedicationDBSchema.MedicationTable.Cols.REMINDE_ME_WHEN, medication.getRemindMeWhen().toString());

        return contentValues;
    }

    private MedicationCursorWrapper queryMedications(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                MedicationDBSchema.MedicationTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new MedicationCursorWrapper(cursor);
    }

    public void addMedication(Medication medication) {
        ContentValues contentValues = getContentValues(medication);
        mDatabase.insert(MedicationDBSchema.MedicationTable.NAME, null, contentValues);

        Log.i("MED: ", "Record Added Successfully");
    }

    public void updateMedication(Medication medication) {
        ContentValues contentValues = getContentValues(medication);

    }

    public List<Medication> getMedicationList() {

        List<Medication> medicationList = new ArrayList<>();
        MedicationCursorWrapper cursorWrapper = queryMedications(null, null);

        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                medicationList.add(cursorWrapper.getMedication());
                cursorWrapper.moveToNext();
            }
        }
        finally {
            cursorWrapper.close();
        }
        return medicationList;
    }

    public void deleteMedication(String id) {
        Log.i("delete",id);
        mDatabase.delete(MedicationDBSchema.MedicationTable.NAME, "uuid" + "='" + id + "'", null);
    }
}