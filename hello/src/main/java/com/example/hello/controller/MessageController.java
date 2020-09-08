package com.example.hello.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.hello.entity.Message;
import com.example.hello.repository.MessageRepository;
import com.example.hello.utils.FileUtil;
import com.example.hello.utils.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class MessageController {


    @Autowired
    private MessageRepository messageRepository;

    //后端解决跨域方法
    public void UploadUserAvatars(HttpServletRequest request,HttpServletResponse response) throws Exception{
        // 指定允许其他域名访问
        response.addHeader("Access-Control-Allow-Origin","*");
        // 响应类型
        response.addHeader("Access-Control-Allow-Methods","POST");
        // 响应头设置
        response.addHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
        //响应内容支持中文
        response.setContentType("text/html;charset=utf-8");
    }


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
    public Iterable<Message> queryTuCaoMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Iterable<Message> message = messageRepository.findAll();
//        System.out.println(user.toString());
        System.out.println(message.toString());

        //解决跨域
        UploadUserAvatars(request,response);

        return messageRepository.findAll();

    }


    @PostMapping("/qrcode")
    @ResponseBody
    public String qrcode(HttpServletRequest request) {
        String url = "http://apis.juhe.cn/qrcode/api";
        String params = "key=a5d6fad65a8123b08da2a45baf8e1be1&type=2&fgcolor=00b7ee&w=90&m=5&text=hello%20world!";

        return HttpRequestUtil.doPost(url,params);
    }


}