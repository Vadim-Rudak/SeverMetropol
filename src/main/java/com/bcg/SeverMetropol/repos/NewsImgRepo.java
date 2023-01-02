package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.NewsImg;

public interface NewsImgRepo {
    void save(NewsImg newsImg);
    int findLastId();
}
