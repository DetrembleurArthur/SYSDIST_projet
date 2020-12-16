package com.bourgedetrembleur.hepl.repository;

import com.bourgedetrembleur.hepl.model.Payement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayementRepository extends CrudRepository<Payement, Integer>
{
    
}
