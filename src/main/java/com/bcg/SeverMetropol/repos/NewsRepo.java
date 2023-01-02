package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.News;

public interface NewsRepo {
    void save(News news);
    int findLastId();
}
