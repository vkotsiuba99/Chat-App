package com.chat.chatserver.controller;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.chat.chatserver.model.Message;
import com.chat.chatserver.model.Status;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ChatController.class})
@ExtendWith(SpringExtension.class)
class ChatControllerTest {
    @Autowired
    private ChatController chatController;

    @MockBean
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * Method under test: {@link ChatController#receivePrivateMessage(Message)}
     */
    @Test
    void testReceivePrivateMessage() throws MessagingException {
        doNothing().when(this.simpMessagingTemplate).convertAndSendToUser((String) any(), (String) any(), (Object) any());
        Message message = new Message("Sender Name", "Receiver Name", "Not all who wander are lost", "2020-03-01",
                Status.JOIN);

        assertSame(message, this.chatController.receivePrivateMessage(message));
        verify(this.simpMessagingTemplate).convertAndSendToUser((String) any(), (String) any(), (Object) any());
    }
}

