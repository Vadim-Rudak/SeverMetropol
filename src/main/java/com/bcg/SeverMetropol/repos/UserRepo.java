package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.User;
import java.util.List;

public interface UserRepo {
    void save(User user);
    void upDate(User user);
    void deleteUserById(int id_user_delete);

    int findLastId();

    User findUserById(int user_id);
    User findByLogin(String login);
    List<User> findAllUsers();

    List<User> findAllUsersWithMoreInfo();
    User findMoreUserInfoById(int user_id);

    List<User> searchMoreUserInfoByFIO(String fio);
}
