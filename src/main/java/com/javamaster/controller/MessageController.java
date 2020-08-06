package com.javamaster.controller;//package com.javamaster.controller;
import com.javamaster.model.MessageModel;
import com.javamaster.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
//it handles incoming messages and sends it to the users
//main logic
@RestController
public class MessageController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, MessageModel message)//sends message from one user to another
    {
     System.out.println("send message:"+ message +"to:"+to) ;
     boolean isExists = UserStorage.getInstance().getUsers().contains(to);
     if(isExists)
     {
         simpMessagingTemplate.convertAndSend("/topic/messages/"+ to, message);
     }
    }

}
