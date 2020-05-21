package com.example.hello.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.hello.entity.Character;
import com.example.hello.entity.Message;
import com.example.hello.repository.CharacterRepository;
import com.example.hello.repository.MessageRepository;
import com.example.hello.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class CharacterController {


    @Autowired
    private CharacterRepository characterRepository;

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

    @PostMapping("/character/setAbility")
    @ResponseBody
    public String characterSetAbility(HttpServletRequest request, HttpServletResponse response) throws Exception {
          JSONObject jsonObject = FileUtil.readRequestParams(request);
//        int hp = jsonObject.getInteger("hp");
//        int mp = jsonObject.getInteger("mp");
//        int strength = jsonObject.getInteger("strength");
//        int defense = jsonObject.getInteger("defense");
//        int dodge = jsonObject.getInteger("dodge");
//        Character character = new Character();
//        character.setHP(hp);
//        character.setMP(mp);
//        character.setStrength(strength);
//        character.setDefense(defense);
//        character.setDodge(dodge);

//        characterRepository.save(character);
//        UploadUserAvatars(request,response);
        return jsonObject.toString();

    }






}