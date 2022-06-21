package com.chat.chatserver.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class StatusTest {
    /**
     * Method under test: {@link Status#values()}
     */
    @Test
    void testValues() {
        Status[] actualValuesResult = Status.values();
        assertEquals(3, actualValuesResult.length);
        assertEquals(Status.JOIN, actualValuesResult[0]);
        assertEquals(Status.MESSAGE, actualValuesResult[1]);
        assertEquals(Status.LEAVE, actualValuesResult[2]);
    }
}

