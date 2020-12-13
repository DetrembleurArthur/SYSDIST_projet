package com.bourgedetrembleur.hepl.repository;

import com.bourgedetrembleur.hepl.model.Command;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandRepository extends CrudRepository<Command, Integer>
{
    Command findByIdSession(String idSession);
}
