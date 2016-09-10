/**
 * Created by Nelson Joseph on 26-May-2016.
 * Reg. No: 75814200014
 * Roll. No: 1406FOSS0011
 */

package com.medreminder;

import java.util.UUID;

public class Medication {

    private UUID mId;
    private String medicineName;
    private String dosage;
    private String reminderTime;
    private String instructions;
    private String totalNumberOfTablets;
    private String remindMeWhen;

    public Medication() {
        this(UUID.randomUUID());
    }

    public Medication(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getTotalNumberOfTablets() {
        return totalNumberOfTablets;
    }

    public void setTotalNumberOfTablets(String totalNumberOfTablets) {
        this.totalNumberOfTablets = totalNumberOfTablets;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public String getRemindMeWhen() {
        return remindMeWhen;
    }

    public void setRemindMeWhen(String remindMeWhen) {
        this.remindMeWhen = remindMeWhen;
    }
}