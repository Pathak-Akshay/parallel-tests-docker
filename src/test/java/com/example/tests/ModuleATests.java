package com.example.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test class for Module A functionality
 */
public class ModuleATests {

    @BeforeClass
    public void setup() {
        System.out.println("Setting up ModuleATests - " + Thread.currentThread().getId());
        // Setup code specific to Module A tests
    }

    @Test(groups = {"moduleA", "smoke"})
    public void testA1() {
        System.out.println("Running testA1 - " + Thread.currentThread().getId());
        // Simulate test execution time
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Assert.assertTrue(true);
    }

    @Test(groups = {"moduleA", "regression"})
    public void testA2() {
        System.out.println("Running testA2 - " + Thread.currentThread().getId());
        // Simulate test execution time
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Assert.assertTrue(true);
    }

    @Test(groups = {"moduleA", "regression"})
    public void testA3() {
        System.out.println("Running testA3 - " + Thread.currentThread().getId());
        // Simulate test execution time
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Assert.assertTrue(true);
    }
}