package com.example.hello.entity;

import com.sun.javafx.beans.IDProperty;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity // This tells Hibernate to make a table out of this class
public class Luckmoney {

    @Id
    @GeneratedValue
    private Integer id ;

    //    @NotNull
    //    @Max()
    //    @DecimalMin()
    private BigDecimal money;

    /**
     * 发送方
     */
    private String producer;

    /**
     * 接受方
     */
    private String consumer;

    public Luckmoney(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    @Override
    public String toString() {
        return "Luckmoney{" +
                "id=" + id +
                ", money=" + money +
                ", producer='" + producer + '\'' +
                ", consumer='" + consumer + '\'' +
                '}';
    }
}



