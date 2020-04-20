package com.example.hello.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.hello.repository.UserRepository;
import com.example.hello.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;


@Controller
public class UserController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    //就当成数据库里的表
    private UserRepository userRepository;

    public static JSONObject readRequestParams(HttpServletRequest request) {
        JSONObject params = new JSONObject();
        try {
            Map requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
//                logger.info(valueStr);
//                try {
//                    valueStr = JSONObject.parseObject(valueStr.toString());
//                } catch (Exception e) {
//
//                }
                params.put(name, valueStr);
            }
            if (params.isEmpty()) {
                InputStream inputStream = request.getInputStream();
                ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];

                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    outSteam.write(buffer, 0, len);
                }
                String paramStr = new String(outSteam.toByteArray(), "UTF-8");
/*
                logger.info("paramStr=" + paramStr);
*/
                params = JSON.parseObject(paramStr);
                outSteam.close();
                inputStream.close();
            }

        } catch (Exception e) {

        }
        return params;
    }

    @PostMapping("/test")
    @ResponseBody
    public String test(HttpServletRequest request) {
        JSONObject jsonObject = readRequestParams(request);
        System.out.println("看这里"+jsonObject);
        System.out.println("<<<<<       >>>>>>");
        return "SUCCESS";
    }

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        return "greeting";
    }


    //注释了responsebody就说明return返回的内容会显示在html的body里，不注释return值就是html的名字
    //@ResponseBody
    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute User user, Model model) {

        model.addAttribute("user", user);

//        User newUser = new User();
//
//        newUser.setName(user.getName());
//        newUser.setAge(user.getAge());
//        newUser.setGender(user.getGender());
//        newUser.setEmail(user.getEmail());
//        newUser.setCity(user.getCity());
        userRepository.save(user);

        return "result";

    }

    @GetMapping("/all")
    public String getMessage(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "all";
    }
    @PostMapping("/all/query")
    public String getUser(@RequestBody User user, Model model) {
        String name = user.getName();
        User userEnity =userRepository.findUserByName(name);
        model.addAttribute("user", userEnity);
        return "all";
    }

    @GetMapping("/insert")
    public String initUser() {


        return "insert";
    }

    @PostMapping("/insert")
    public String insertUserInfo() {
//        System.out.println(user);
//        user.setAge(13);
//        user.setName("yk");
//        userRepository.save(user);
//        System.out.println(user.toString());

        return "success";
    }

    @GetMapping("/success")
    public String show() {
        return "success";
    }
//    前端
//   $.ajax({
//        type: "POST",
//                url: baseURL + url,
//                contentType: "application/json",
//                data : JSON.stringify(vm.noticeInfo)
//
//        后台 ，有两种方式
//        public R update(@RequestBody NoticeInfoEntity noticeInfo)
//
//        可以直接使用一个map来接收。
//
//        public R update(@RequestBody Map<String, Object> params)
    //
    @GetMapping("/query/returnPage")
    public String returnPage(Model model) {
        model.addAttribute("user",new User());
        return "query";
    }

    //model就可以避免你再去写一个方法用来返回json数据了
    @PostMapping("/query/query")
    @ResponseBody
    public User queryUser(@RequestBody User user, Model model) {
        String name = user.getName();
        User userEnity =userRepository.findUserByName(name);


        return userEnity;
    }



//    @PostMapping("/query/searchUser")
//    @ResponseBody
//    public String queryUser2(@RequestBody User user) {
//        String name = user.getName();
//        System.out.println(name);
//        User userEnity =userRepository.findUserByName(name);
//        System.out.println(userEnity.getAge());
//        System.out.println(userEnity.getName());
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("user",userEnity);
//
//        return jsonObject.toJSONString();
//    }

//    String json = JSONObject.toJSONString(map);
//    JSONObject jsonObject = JSONObject.parseObject(json);

    @GetMapping("/intoUrl")
    public String showPage(){

        return "forTest";
    }

    @GetMapping("/yourUrl")
    @ResponseBody
    public User queryUser(@RequestParam("yourDataName")String name){

        return userRepository.findUserByName(name);
    }


}