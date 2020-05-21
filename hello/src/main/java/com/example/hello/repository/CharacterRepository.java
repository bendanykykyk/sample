
package com.example.hello.repository;

import com.example.hello.entity.Character;
import com.example.hello.entity.Message;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CharacterRepository extends CrudRepository<Character, Integer> {






}
