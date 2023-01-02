package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.News;

import java.util.List;

public interface NewsRepo {
    void save(News news);
    int findLastId();
    List<News> getAll();
}
