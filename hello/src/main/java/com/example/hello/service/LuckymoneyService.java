package com.example.hello.service;

import com.example.hello.enums.ResultEnum;
import com.example.hello.exception.LuckymoneyException;
import com.example.hello.repository.LuckymoneyRepository;
import com.example.hello.entity.Luckmoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

//事务，用来处理多个请求，要求一起成功或者一起失败的时候使用
@Service
public class LuckymoneyService {

    @Autowired
    LuckymoneyRepository repository;

    @Transactional
    public void createTwo(){
        Luckmoney luckmoney = new Luckmoney();
        luckmoney.setProducer("yk");
        luckmoney.setMoney(new BigDecimal("520"));

        Luckmoney luckmoney2 = new Luckmoney();
        luckmoney2.setProducer("yk");
        luckmoney2.setMoney(new BigDecimal("1314"));
        repository.save(luckmoney);
        repository.save(luckmoney2);

    }

    public Luckmoney getAge(Integer id) throws Exception {
        Luckmoney luckmoney = repository.findById(id).orElse(null);
        BigDecimal bd = new BigDecimal("50");

        if ((luckmoney.getMoney()).compareTo(bd)==-1){
            //如果luckymoney小于50
                throw new LuckymoneyException(ResultEnum.PAY_LESS_THAN_50);
        }else if ((luckmoney.getMoney()).compareTo(bd)==0){
            //如果luckymoney等于50
                throw new LuckymoneyException(ResultEnum.PAY_EQUAL_50);
        }
            return luckmoney;


    }
}
