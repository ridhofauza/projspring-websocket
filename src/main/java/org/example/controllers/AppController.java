package org.example.controllers;

import org.example.model.MessageModel;
import org.example.model.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AppController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @ResponseBody
    @RequestMapping("/chatpage")
    public String chatPageHtml(Model model) {
        return "/static/chatpage.html";
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(final MessageModel messageModel) throws Exception {
        final String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(messageModel.getFrom(), messageModel.getText(), time);
    }

}
