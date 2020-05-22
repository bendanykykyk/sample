package com.example.hello.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.hello.entity.Characters;
import com.example.hello.repository.CharactersRepository;
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
    private CharactersRepository charactersRepository;

    //后端解决跨域方法
    public void UploadUserAvatars(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 指定允许其他域名访问
        //response.addHeader("Access-Control-Allow-Origin","*");
        // 响应类型
        response.addHeader("Access-Control-Allow-Methods", "POST");
        // 响应头设置
        response.addHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        //响应内容支持中文
        response.setContentType("text/html;charset=utf-8");
    }

    //人物设置能力值
    @PostMapping("/character/setAbility")
    @ResponseBody
    public String characterSetAbility(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = FileUtil.readRequestParams(request);
        int hp = jsonObject.getInteger("hp");
        int mp = jsonObject.getInteger("mp");
        int strength = jsonObject.getInteger("strength");
        int defense = jsonObject.getInteger("defense");
        int dodge = jsonObject.getInteger("dodge");

        Characters character = new Characters();
        character.setHp(hp);
        character.setMp(mp);
        character.setStrength(strength);
        character.setDefense(defense);
        character.setDodge(dodge);

        charactersRepository.save(character);
        UploadUserAvatars(request, response);

        JSONObject characterJsonobject = new JSONObject();
        characterJsonobject.put("id",character.getId());
        System.out.println(characterJsonobject.toString());
        return characterJsonobject.toString();
    }

    //人物普通攻击
    @PostMapping("/character/normalAttack")
    @ResponseBody
    public double characterNormalAttack(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = FileUtil.readRequestParams(request);
        int id = jsonObject.getInteger("id");
        Characters characters = charactersRepository.findCharactersById(id);
        double damage = characters.getStrength() * 2;
        UploadUserAvatars(request, response);

        return damage;
    }

    //人物防御
    @PostMapping("/character/defense")
    @ResponseBody
    public double characterDefense(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = FileUtil.readRequestParams(request);
        int id = jsonObject.getInteger("id");
        Characters characters = charactersRepository.findCharactersById(id);
        double defense = characters.getDefense() * 1.5;
        UploadUserAvatars(request, response);

        return defense;
    }

    //人物逃跑
    @PostMapping("/character/flee")
    @ResponseBody
    public int characterFlee() {

        int isFleeSuccess;
        int number = (int)(Math.random()*100);
        if(number<=40){
            isFleeSuccess = 1;
        }else {
            isFleeSuccess = 0;
        }


        return isFleeSuccess;
    }

    //查询人物状态
    @PostMapping("/character/queryStatus")
    @ResponseBody
    public String characterQueryStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = FileUtil.readRequestParams(request);
        int id = jsonObject.getInteger("id");
        Characters characters = charactersRepository.findCharactersById(id);

        JSONObject character = new JSONObject();
        character.put("defense",characters.getDefense());
        character.put("dodge",characters.getDodge());
        character.put("hp",characters.getHp());
        character.put("mp",characters.getMp());
        character.put("strength",characters.getStrength());
        character.put("exp",characters.getExp());

        UploadUserAvatars(request, response);

        return character.toString();
    }



}