package com.bourgedetrembleur.hepl.model.repository;

import com.bourgedetrembleur.hepl.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long> {
}
