package com.example.hello.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Http请求的最外层返回对象
 */
@Entity
public class Character {
    @Id
    @GeneratedValue
    private int Id;





}
