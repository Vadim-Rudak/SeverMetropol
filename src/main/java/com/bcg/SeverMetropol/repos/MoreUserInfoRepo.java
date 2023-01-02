package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.MoreUserInfo;

public interface MoreUserInfoRepo {
    void save(MoreUserInfo moreUserInfo);
    void update(MoreUserInfo moreUserInfo);
    void delete(int id_user_delete);
    int findLastId();
}
