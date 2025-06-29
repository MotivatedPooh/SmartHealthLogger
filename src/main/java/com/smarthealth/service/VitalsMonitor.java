package com.smarthealth.service;

import com.smarthealth.model.Patient;
import com.smarthealth.model.Vitals;
import com.smarthealth.util.Logger;

public class VitalsMonitor implements Runnable {
    private final Patient patient;
    private final Vitals vitals;

    public VitalsMonitor(Patient patient, Vitals vitals) {
        this.patient = patient;
        this.vitals = vitals;
    }

    @Override
    public void run() {
        Logger logger = Logger.getInstance();
        String summary = vitals.getHealthSummary(patient);
        logger.info(summary);
    }
}
