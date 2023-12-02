package ru.studentproject.startbusiness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.studentproject.startbusiness.models.ChatMessage;
import ru.studentproject.startbusiness.models.User;
import ru.studentproject.startbusiness.service.UserService;

@Controller

public class ChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private UserService userService;
    @MessageMapping("/chat/{toId}")
    public void sendMessage(@DestinationVariable Long toId,ChatMessage message){
        System.out.println("toId = " + toId + ", message = " + message);
        User toUser = userService.findById(toId);
        if (toUser == null){
            System.out.println("user not found");
        }
        else{
            messagingTemplate.convertAndSend("/topic/messages/"+toId,message);
        }

    }
}

