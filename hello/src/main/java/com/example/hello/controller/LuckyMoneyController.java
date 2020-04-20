package com.example.hello.controller;

import com.example.hello.repository.LuckymoneyRepository;
import com.example.hello.entity.Luckmoney;
import com.example.hello.service.LuckymoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
public class LuckyMoneyController {

//    @Autowired
//    private LimitConifg limitConifg;
//
//    //注释地方 command+P有提示
//    @GetMapping({"/hello/{id}","/hi"})
//    @ResponseBody
//    public String say(@RequestParam(value="id",required = false,defaultValue = "0") Integer MyId) {
//
//        return "说明："+limitConifg.getDescription()+MyId;
//
//    }

//    使用路径变量  url->后端->前端 1.@PathVariable 在请求链接后增加 /100 ，这个100就是id
//    @GetMapping({"/hello/{id}","/hi"})
//    @ResponseBody
//    public String say2(@PathVariable("id") Integer MyId) {
//
//        return "说明："+limitConifg.getDescription()+MyId;
//
//    }


    //使用请求参数 url->后端->前端 2.RequestParam 在请求链接后增加 ?id=100   id必须和@RequestParam中设的相同
    //@RequestParam还能设置参数的一些基本属性，比如说必填不必填，默认值之类的
//    @GetMapping("/hi")
//    @ResponseBody
//    public String say(@RequestParam(value="id",required = false,defaultValue = "0") Integer MyId) {
//
//        return "说明："+limitConifg.getDescription()+MyId;
//
//    }

        /**
         * 获取红包列表
         */
        @Autowired
        private LuckymoneyRepository repository;

        @GetMapping("/luckymoneys")
        @ResponseBody
        public List<Luckmoney> list(){
            return repository.findAll();
        }

//        /**
//         *创建红包
//         */
//        @PostMapping("/luckymoneys")
//        @ResponseBody
//        public Luckmoney create(@RequestParam("producer") String producer,
//                                @RequestParam("money") BigDecimal money){
//                Luckmoney luckmoney = new Luckmoney();
//                luckmoney.setProducer(producer);
//                luckmoney.setMoney(money);
//                return reposity.save(luckmoney);
//        }

        /**
         *改造后的创建红包
         */
        @PostMapping("/luckymoneys")
        @ResponseBody
        public Luckmoney create(@Valid Luckmoney luckmoney , BindingResult bindingResult){
                if (bindingResult.hasErrors()){
                        System.out.println(bindingResult.getFieldError().getDefaultMessage());
                        return null;
                }
                luckmoney.setProducer(luckmoney.getProducer());
                luckmoney.setMoney(luckmoney.getMoney());
                return repository.save(luckmoney);
        }

        /**
         * 通过id查询红包
         */
        @PostMapping("/luckymoneys/query")
        @ResponseBody
        public Luckmoney findById(@RequestParam("id") Integer id){

                return repository.findById(id).orElse(null);
        }

        /**
         * 更新红包（领红包）
         */
        /**
         * 通过id查询红包
         */
        @PostMapping("/luckymoneys/get")
        @ResponseBody
        public Luckmoney getById(@RequestParam("id") Integer id,
                                 @RequestParam("consumer") String consumer){
                Optional<Luckmoney> optional= repository.findById(id);

                if (optional.isPresent()){
                        Luckmoney luckmoney = optional.get();
                        luckmoney.setId(id);
                        luckmoney.setConsumer(consumer);
                        return repository.save(luckmoney);
                }

                return  null;

        }

        @Autowired
        private LuckymoneyService service;
//要注意没返回值的话，@AfterRuturning就获取不到值了 toString也就失败了
        @PostMapping("/luckymoneys/createTwo")
        @ResponseBody
        public void createTwo(){
                service.createTwo();
        }


        @PostMapping("/luckymoneys/getAge")
        @ResponseBody
        public Luckmoney getAge(@RequestParam("id") Integer id) throws Exception {
               return service.getAge(id);
        }




}