package com.example.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Custom TestNG listener to track test execution progress and timings
 */
public class TestListener implements ITestListener {

    private long startTime;

    @Override
    public void onStart(ITestContext context) {
        startTime = System.currentTimeMillis();
        System.out.println("======================================================");
        System.out.println("Starting Test Suite: " + context.getName());
        System.out.println("Container ID: " + System.getenv().getOrDefault("HOSTNAME", "local"));
        System.out.println("======================================================");
    }

    @Override
    public void onFinish(ITestContext context) {
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("======================================================");
        System.out.println("Finished Test Suite: " + context.getName());
        System.out.println("Total tests: " + context.getAllTestMethods().length);
        System.out.println("Passed: " + context.getPassedTests().size());
        System.out.println("Failed: " + context.getFailedTests().size());
        System.out.println("Skipped: " + context.getSkippedTests().size());
        System.out.println("Total time: " + (duration / 1000.0) + " seconds");
        System.out.println("Container ID: " + System.getenv().getOrDefault("HOSTNAME", "local"));
        System.out.println("======================================================");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting test: " + result.getName() + " [Container: " +
                System.getenv().getOrDefault("HOSTNAME", "local") + "]");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        long duration = result.getEndMillis() - result.getStartMillis();
        System.out.println("PASSED: " + result.getName() + " - Duration: " +
                (duration / 1000.0) + " seconds" + " [Container: " +
                System.getenv().getOrDefault("HOSTNAME", "local") + "]");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        long duration = result.getEndMillis() - result.getStartMillis();
        System.out.println("FAILED: " + result.getName() + " - Duration: " +
                (duration / 1000.0) + " seconds" + " [Container: " +
                System.getenv().getOrDefault("HOSTNAME", "local") + "]");
        System.out.println("Reason: " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("SKIPPED: " + result.getName() + " [Container: " +
                System.getenv().getOrDefault("HOSTNAME", "local") + "]");
    }
}