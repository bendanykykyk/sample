
package com.example.hello.repository;

import com.example.hello.entity.Characters;
import com.example.hello.entity.Monster;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MonsterRepository extends CrudRepository<Monster, Long> {


    Monster findMonsterById(int id);



}
