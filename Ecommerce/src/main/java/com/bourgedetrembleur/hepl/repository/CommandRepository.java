package com.bourgedetrembleur.hepl.repository;

import com.bourgedetrembleur.hepl.model.Command;
import com.bourgedetrembleur.hepl.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommandRepository extends CrudRepository<Command, Integer>
{
    Iterable<Command> findAllByUser(User user);
}
