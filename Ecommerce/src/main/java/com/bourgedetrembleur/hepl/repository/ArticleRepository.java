package com.bourgedetrembleur.hepl.repository;

import com.bourgedetrembleur.hepl.model.Article;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long>
{
    public static final int PAGE_SIZE = 5;
}
