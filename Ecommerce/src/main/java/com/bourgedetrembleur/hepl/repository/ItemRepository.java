package com.bourgedetrembleur.hepl.repository;

import com.bourgedetrembleur.hepl.model.Item;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, Integer>
{
    public static final int PAGE_SIZE = 5;
}
