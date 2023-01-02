package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.Photo;

public interface PhotoRepo {
    void save(Photo photo);
    void updatePhoto(Photo photo);
    int findLastPhotoId();
    Photo findUserPhoto(int user_id);
}
