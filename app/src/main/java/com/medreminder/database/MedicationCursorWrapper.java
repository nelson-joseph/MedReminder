/**
 * Created by Nelson Joseph on 26-May-2016.
 * Reg. No: 75814200014
 * Roll. No: 1406FOSS0011
 */

package com.medreminder.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.medreminder.Medication;

import java.util.UUID;

public class MedicationCursorWrapper extends CursorWrapper {

    public MedicationCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Medication getMedication() {
        String uuidString = getString(getColumnIndex(MedicationDBSchema.MedicationTable.Cols.UUID));
        String medicineName = getString(getColumnIndex(MedicationDBSchema.MedicationTable.Cols.MEDICINE_NAME));
        String dosage = getString(getColumnIndex(MedicationDBSchema.MedicationTable.Cols.DOSAGE));
        String reminderTime = getString(getColumnIndex(MedicationDBSchema.MedicationTable.Cols.REMINDER_TIME));
        String instructions = getString(getColumnIndex(MedicationDBSchema.MedicationTable.Cols.INSTRUCTIONS));
        String totalNumberOfTablets = getString(getColumnIndex(MedicationDBSchema.MedicationTable.Cols.TOTAL_NO_OF_TABLETS));
        String remindMeWhen = getString(getColumnIndex(MedicationDBSchema.MedicationTable.Cols.REMINDE_ME_WHEN));

        Medication medication = new Medication(UUID.fromString(uuidString));
        medication.setMedicineName(medicineName);
        medication.setDosage(dosage);
        medication.setReminderTime(reminderTime);
        medication.setInstructions(instructions);
        medication.setTotalNumberOfTablets(totalNumberOfTablets);
        medication.setRemindMeWhen(remindMeWhen);

        return medication;
    }
}