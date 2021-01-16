package com.bourgedetrembleur.hepl.model;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock, Integer>
{
    Stock findStockBySupplierAndIdArticle(String supplier, int idArticle);
}
