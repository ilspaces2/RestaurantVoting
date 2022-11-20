package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.javaops.bootjava.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

//    @Query("from User u join fetch u.roles")
//    List<User> findAll();
}
