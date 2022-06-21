package com.chat.chatserver.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MessageTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Message#Message()}
     *   <li>{@link Message#setDate(String)}
     *   <li>{@link Message#setMessage(String)}
     *   <li>{@link Message#setReceiverName(String)}
     *   <li>{@link Message#setSenderName(String)}
     *   <li>{@link Message#setStatus(Status)}
     *   <li>{@link Message#getDate()}
     *   <li>{@link Message#getMessage()}
     *   <li>{@link Message#getReceiverName()}
     *   <li>{@link Message#getSenderName()}
     *   <li>{@link Message#getStatus()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Message actualMessage = new Message();
        actualMessage.setDate("2020-03-01");
        actualMessage.setMessage("Not all who wander are lost");
        actualMessage.setReceiverName("Receiver Name");
        actualMessage.setSenderName("Sender Name");
        actualMessage.setStatus(Status.JOIN);
        assertEquals("2020-03-01", actualMessage.getDate());
        assertEquals("Not all who wander are lost", actualMessage.getMessage());
        assertEquals("Receiver Name", actualMessage.getReceiverName());
        assertEquals("Sender Name", actualMessage.getSenderName());
        assertEquals(Status.JOIN, actualMessage.getStatus());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Message#Message(String, String, String, String, Status)}
     *   <li>{@link Message#setDate(String)}
     *   <li>{@link Message#setMessage(String)}
     *   <li>{@link Message#setReceiverName(String)}
     *   <li>{@link Message#setSenderName(String)}
     *   <li>{@link Message#setStatus(Status)}
     *   <li>{@link Message#getDate()}
     *   <li>{@link Message#getMessage()}
     *   <li>{@link Message#getReceiverName()}
     *   <li>{@link Message#getSenderName()}
     *   <li>{@link Message#getStatus()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Message actualMessage = new Message("Sender Name", "Receiver Name", "Not all who wander are lost", "2020-03-01",
                Status.JOIN);
        actualMessage.setDate("2020-03-01");
        actualMessage.setMessage("Not all who wander are lost");
        actualMessage.setReceiverName("Receiver Name");
        actualMessage.setSenderName("Sender Name");
        actualMessage.setStatus(Status.JOIN);
        assertEquals("2020-03-01", actualMessage.getDate());
        assertEquals("Not all who wander are lost", actualMessage.getMessage());
        assertEquals("Receiver Name", actualMessage.getReceiverName());
        assertEquals("Sender Name", actualMessage.getSenderName());
        assertEquals(Status.JOIN, actualMessage.getStatus());
    }
}

