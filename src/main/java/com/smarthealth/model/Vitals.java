package com.smarthealth.model;

public class Vitals {
    private final int heartRate;
    private final int systolicBP;
    private final int diastolicBP;
    private final double bloodSugarMgDl;

    public Vitals(int heartRate, int systolicBP, int diastolicBP, double bloodSugarMgDl) {
        this.heartRate = heartRate;
        this.systolicBP = systolicBP;
        this.diastolicBP = diastolicBP;
        this.bloodSugarMgDl = bloodSugarMgDl;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public int getSystolicBP() {
        return systolicBP;
    }

    public int diastolicBP() {
        return diastolicBP;
    }

    public double getBloodSugarMgDl() {
        return bloodSugarMgDl;
    }

    public boolean isCritical(Patient patient) {
        boolean hrCritical = heartRate < 50 || heartRate > 120;
        boolean bpCritical =
                systolicBP < 90 || systolicBP > 180 ||
                        diastolicBP < 60 || diastolicBP > 120;

        boolean sugarCritical;
        if (patient.getAge() >= 60) {
            sugarCritical = bloodSugarMgDl < 80 || bloodSugarMgDl > 160;
        } else {
            sugarCritical = bloodSugarMgDl < 70 || bloodSugarMgDl > 140;
        }

        return hrCritical || bpCritical || sugarCritical;
    }

    public String getBloodPressureCategory() {
        if (systolicBP > 180 || diastolicBP > 120) {
            return "Hypertensive Crisis";
        } else if (systolicBP >= 140 || diastolicBP >= 90) {
            return "Hypertension Stage 2";
        } else if (systolicBP >= 130 || diastolicBP >= 80) {
            return "Hypertension Stage 1";
        } else if (systolicBP >= 120) {
            return "Elevated";
        } else if (systolicBP >= 90 && diastolicBP >= 60) {
            return "Normal";
        } else {
            return "Low Blood Pressure";
        }
    }

    public String getHealthSummary(Patient patient) {
        StringBuilder summary = new StringBuilder();

        summary.append("\n---- Health Summary for ").append(patient.getName()).append(" ----\n");
        summary.append("Age: ").append(patient.getAge()).append(", Sex: ").append(patient.getSex()).append("\n");
        summary.append("Heart Rate: ").append(heartRate).append(" bpm\n");
        summary.append("Blood Pressure: ").append(systolicBP).append("/").append(diastolicBP)
                .append(" mmHg (").append(getBloodPressureCategory()).append(")\n");
        summary.append("Blood Sugar: ").append(bloodSugarMgDl).append(" mg/dL\n");
        summary.append("Vitals Status: ").append(isCritical(patient) ? "Abnormal" : "Normal").append("\n");

        return summary.toString();
    }
}
