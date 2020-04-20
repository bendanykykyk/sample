
package com.example.hello.repository;

        import com.example.hello.entity.User;
        import org.springframework.data.repository.CrudRepository;

        import java.awt.print.Pageable;
        import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {

        //这是我加的
        User findUserByName(String name);




}
