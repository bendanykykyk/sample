package com.example.hello.repository;

import com.example.hello.entity.Luckmoney;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LuckymoneyRepository extends JpaRepository<Luckmoney,Integer> {
}
