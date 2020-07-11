package org.jason.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class HelloWebSocketController {

    @Autowired
    public SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public HelloWebSocketController(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/hello")
    @SendTo("/client/hello")
    public HelloMessage greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new HelloMessage("Hello, " + message.getName() + "!");
    }

    @Scheduled(fixedRate = 5000)
    public void sendGreeting() throws Exception {
        this.messagingTemplate.convertAndSend("/client/hello", new HelloMessage("Hello, " + Math.random() * 10 + "!"));
    }

}
