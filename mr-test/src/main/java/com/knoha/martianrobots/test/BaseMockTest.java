package com.knoha.martianrobots.test;

import com.knoha.martianrobots.utils.log.ApplicationLog;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;

public abstract class BaseMockTest {

    private static final ApplicationLog LOG = new ApplicationLog(BaseMockTest.class.getSimpleName());

    private static String testName;
    private static long startTime;

    public BaseMockTest() {
        testName = this.getClass().getName();
    }

    @BeforeClass
    public static void prepareTestClass() {
        startTime = System.currentTimeMillis();
    }

    @AfterClass
    public static void finishTestClass() {
        startTime = System.currentTimeMillis();
        LOG.print("--- Test [{0}] took {1}ms.", testName, System.currentTimeMillis() - startTime);
    }

    @Before
    public void start() throws Exception {
        prepareMocks();
    }

    @After
    public void finish() throws Exception {
        Mockito.validateMockitoUsage();
    }

    protected abstract void prepareMocks() throws Exception;

}
