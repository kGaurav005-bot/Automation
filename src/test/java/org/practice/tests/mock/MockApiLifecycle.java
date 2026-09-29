package org.practice.tests.mock;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class MockApiLifecycle {

    @BeforeSuite
    public void startMockApi() {

        MockApiServer.start();
    }

    @AfterSuite
    public void stopMockApi() {

        MockApiServer.stop();
    }
}