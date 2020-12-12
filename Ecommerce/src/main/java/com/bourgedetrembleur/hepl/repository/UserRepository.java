package com.bourgedetrembleur.hepl.repository;

import com.bourgedetrembleur.hepl.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer>
{
    @Query("select u from User u where u.username = :username")
    User getUserByUsername(@Param("username") String username);
}
