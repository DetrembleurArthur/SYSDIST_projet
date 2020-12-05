package com.bourgedetrembleur.hepl.model.repository;

import com.bourgedetrembleur.hepl.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>
{
    List<Client> findAll();
}
