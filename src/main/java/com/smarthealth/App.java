package com.smarthealth;

import com.smarthealth.model.Patient;
import com.smarthealth.model.Vitals;
import com.smarthealth.service.VitalsMonitor;
import com.smarthealth.util.Logger;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of patients to check in: ");
        int numPatients = Integer.parseInt(scanner.nextLine());

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < numPatients; i++) {
            System.out.println("\n--- Enter details for patient " + (i + 1) + " ---");

            System.out.print("Patient ID: ");
            String id = scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Sex (MALE/FEMALE): ");
            String sex = scanner.nextLine();

            System.out.print("Heart rate (BPM): ");
            int heartRate = Integer.parseInt(scanner.nextLine());

            System.out.print("Systolic BP: ");
            int systolic = Integer.parseInt(scanner.nextLine());

            System.out.print("Diastolic BP: ");
            int diastolic = Integer.parseInt(scanner.nextLine());

            System.out.print("Blood sugar (mg/dL): ");
            double sugar = Double.parseDouble(scanner.nextLine());

            Patient patient = new Patient(id, name, age, sex);
            Vitals vitals = new Vitals(heartRate, systolic, diastolic, sugar);
            executor.submit(new VitalsMonitor(patient, vitals));
        }

        executor.shutdown();

        try {
            if(!executor.awaitTermination(2, TimeUnit.SECONDS)){
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        logger.getInstance().close();
        scanner.close();
    }
}