package com.example.hello.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.hello.entity.Message;
import com.example.hello.repository.MessageRepository;
import com.example.hello.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;


    @PostMapping("/insertTuCaoMessage")
    @ResponseBody
    public String insertTuCaoMessage(HttpServletRequest request) {
        JSONObject jsonObject = FileUtil.readRequestParams(request);
        String subject = jsonObject.getString("subject");
        String content = jsonObject.getString("content");
        Message message = new Message();
        message.setSubject(subject);
        message.setContent(content);
        messageRepository.save(message);
//        System.out.println(user.toString());
        return jsonObject.toString();

    }

    @PostMapping("/queryTuCaoMessage")
    @ResponseBody
    public Iterable<Message> queryTuCaoMessage(HttpServletRequest request) {

        Iterable<Message> message = messageRepository.findAll();
//        System.out.println(user.toString());
        System.out.println(message.toString());
        return messageRepository.findAll();

    }


}