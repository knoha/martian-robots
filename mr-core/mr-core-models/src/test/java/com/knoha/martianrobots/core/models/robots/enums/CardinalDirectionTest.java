package com.knoha.martianrobots.core.models.robots.enums;

import com.knoha.martianrobots.test.BaseMockTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardinalDirectionTest extends BaseMockTest {

    @Override
    protected void prepareMocks() {
        // Nothing to prepare here
    }

    @Test
    public void getDirectionName() {
        assertEquals("North", CardinalDirection.N.getDirectionName());
        assertEquals("East", CardinalDirection.E.getDirectionName());
        assertEquals("South", CardinalDirection.S.getDirectionName());
        assertEquals("West", CardinalDirection.W.getDirectionName());
    }

}