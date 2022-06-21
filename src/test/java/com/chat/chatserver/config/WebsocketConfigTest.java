package com.chat.chatserver.config;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.config.SimpleBrokerRegistration;
import org.springframework.messaging.support.ExecutorSubscribableChannel;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.socket.config.annotation.WebMvcStompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebMvcStompWebSocketEndpointRegistration;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.ExceptionWebSocketHandlerDecorator;
import org.springframework.web.socket.messaging.SubProtocolWebSocketHandler;

@ContextConfiguration(classes = {WebsocketConfig.class})
@ExtendWith(SpringExtension.class)
class WebsocketConfigTest {
    @Autowired
    private WebsocketConfig websocketConfig;

    /**
     * Method under test: {@link WebsocketConfig#configureMessageBroker(MessageBrokerRegistry)}
     */
    @Test
    void testConfigureMessageBroker() {
        this.websocketConfig.configureMessageBroker(
                new MessageBrokerRegistry(new ExecutorSubscribableChannel(), mock(MessageChannel.class)));
    }

    /**
     * Method under test: {@link WebsocketConfig#configureMessageBroker(MessageBrokerRegistry)}
     */
    @Test
    void testConfigureMessageBroker1() {
        MessageBrokerRegistry messageBrokerRegistry = mock(MessageBrokerRegistry.class);
        when(messageBrokerRegistry.setApplicationDestinationPrefixes((String[]) any()))
                .thenReturn(new MessageBrokerRegistry(new ExecutorSubscribableChannel(), mock(MessageChannel.class)));
        when(messageBrokerRegistry.setUserDestinationPrefix((String) any()))
                .thenReturn(new MessageBrokerRegistry(new ExecutorSubscribableChannel(), mock(MessageChannel.class)));
        when(messageBrokerRegistry.enableSimpleBroker((String[]) any())).thenReturn(new SimpleBrokerRegistration(
                new ExecutorSubscribableChannel(), mock(MessageChannel.class), new String[]{"Destination Prefixes"}));
        this.websocketConfig.configureMessageBroker(messageBrokerRegistry);
        verify(messageBrokerRegistry).setApplicationDestinationPrefixes((String[]) any());
        verify(messageBrokerRegistry).setUserDestinationPrefix((String) any());
        verify(messageBrokerRegistry).enableSimpleBroker((String[]) any());
    }

    /**
     * Method under test: {@link WebsocketConfig#registerStompEndpoints(org.springframework.web.socket.config.annotation.StompEndpointRegistry)}
     */
    @Test
    void testRegisterStompEndpoints() {
        ExceptionWebSocketHandlerDecorator exceptionWebSocketHandlerDecorator = mock(
                ExceptionWebSocketHandlerDecorator.class);
        MessageChannel clientInboundChannel = mock(MessageChannel.class);
        when(exceptionWebSocketHandlerDecorator.getLastHandler())
                .thenReturn(new SubProtocolWebSocketHandler(clientInboundChannel, new ExecutorSubscribableChannel()));
        WebSocketTransportRegistration transportRegistration = new WebSocketTransportRegistration();
        WebMvcStompEndpointRegistry webMvcStompEndpointRegistry = new WebMvcStompEndpointRegistry(
                exceptionWebSocketHandlerDecorator, transportRegistration, new ConcurrentTaskScheduler());

        this.websocketConfig.registerStompEndpoints(webMvcStompEndpointRegistry);
        verify(exceptionWebSocketHandlerDecorator).getLastHandler();
        assertTrue(webMvcStompEndpointRegistry
                .getHandlerMapping() instanceof org.springframework.web.socket.server.support.WebSocketHandlerMapping);
    }
}

