package com.example.hello.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.hello.entity.Characters;
import com.example.hello.entity.Monster;
import com.example.hello.repository.CharactersRepository;
import com.example.hello.repository.MonsterRepository;
import com.example.hello.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class MonsterController {


    @Autowired
    private MonsterRepository monsterRepository;

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

    //怪物设置能力值
    @PostMapping("/monster/setAbility")
    @ResponseBody
    public String monsterSetAbility(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = FileUtil.readRequestParams(request);
        int hp = jsonObject.getInteger("hp");
        int mp = jsonObject.getInteger("mp");
        int strength = jsonObject.getInteger("strength");
        int defense = jsonObject.getInteger("defense");
        int dodge = jsonObject.getInteger("dodge");

        Monster monster = new Monster();
        monster.setHp(hp);
        monster.setMp(mp);
        monster.setStrength(strength);
        monster.setDefense(defense);
        monster.setDodge(dodge);

        monsterRepository.save(monster);
        UploadUserAvatars(request, response);

        JSONObject monsterJsonobject = new JSONObject();
        monsterJsonobject.put("id",monster.getId());
        System.out.println(monsterJsonobject.toString());
        return monsterJsonobject.toString();
    }

    //怪物普通攻击
    @PostMapping("/monster/normalAttack")
    @ResponseBody
    public double monsterNormalAttack(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = FileUtil.readRequestParams(request);
        int id = jsonObject.getInteger("id");
        Monster monster = monsterRepository.findMonsterById(id);
        double damage = monster.getStrength() * 2;
        UploadUserAvatars(request, response);

        return damage;
    }

    //怪物防御
    @PostMapping("/monster/defense")
    @ResponseBody
    public double monsterDefense(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = FileUtil.readRequestParams(request);
        int id = jsonObject.getInteger("id");
        Monster monster = monsterRepository.findMonsterById(id);
        double defense = monster.getDefense() * 1.5;
        UploadUserAvatars(request, response);

        return defense;
    }

//    //怪物逃跑
//    @PostMapping("/character/flee")
//    @ResponseBody
//    public int characterFlee() {
//
//        int isFleeSuccess;
//        int number = (int)(Math.random()*100);
//        if(number<=40){
//            isFleeSuccess = 1;
//        }else {
//            isFleeSuccess = 0;
//        }
//
//
//        return isFleeSuccess;
//    }

    //查询怪物状态
    @PostMapping("/monster/queryStatus")
    @ResponseBody
    public String characterQueryStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = FileUtil.readRequestParams(request);
        int id = jsonObject.getInteger("id");
        Monster monster = monsterRepository.findMonsterById(id);

        JSONObject monsterJsonobject = new JSONObject();
        monsterJsonobject.put("defense",monster.getDefense());
        monsterJsonobject.put("dodge",monster.getDodge());
        monsterJsonobject.put("hp",monster.getHp());
        monsterJsonobject.put("mp",monster.getMp());
        monsterJsonobject.put("strength",monster.getStrength());
        monsterJsonobject.put("exp",monster.getExp());

        UploadUserAvatars(request, response);

        return monsterJsonobject.toString();
    }



}