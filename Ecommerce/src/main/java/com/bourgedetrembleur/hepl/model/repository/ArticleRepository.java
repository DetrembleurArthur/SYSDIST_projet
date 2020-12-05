package com.bourgedetrembleur.hepl.model.repository;

import com.bourgedetrembleur.hepl.model.Article;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long>
{
}
