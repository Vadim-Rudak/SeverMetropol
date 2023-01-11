package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.User;
import com.bcg.SeverMetropol.maper.userMaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class UserImpl implements UserRepo {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public UserImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void save(User user) {
        String sql = "INSERT INTO user_profile (id, name, last_name, patronymic, role, login, password, active) VALUES (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getId(),user.getName(),user.getLast_name(),user.getPatronymic(),user.getRole(),user.getLogin(),user.getPassword(),user.isActive());
    }

    @Override
    public void upDate(User user) {
        String sql ="UPDATE user_profile SET name=?,last_name=?,patronymic=?,role=?,login=?,password=?,active=? WHERE id=?";
        jdbcTemplate.update(sql,user.getName(),user.getLast_name(),user.getPatronymic(),user.getRole(),user.getLogin(),user.getPassword(),user.isActive(),user.getId());
    }

    @Override
    public void deleteUserById(int id_user_delete) {
        String sql = "DELETE FROM user_profile WHERE id=?";
        jdbcTemplate.update(sql, id_user_delete);
    }

    @Override
    public int findLastId() {
        String sql = "SELECT MAX(id) FROM user_profile";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public User findUserById(int user_id) {
        String sql = "SELECT * FROM user_profile WHERE id=?";
        return jdbcTemplate.queryForObject(sql,new userMaper(),user_id);
    }

    @Override
    public User findByLogin(String login) {
        String sql = "SELECT * FROM user_profile WHERE login=?";
        return jdbcTemplate.queryForObject(sql,new userMaper(),login);
    }

    @Override
    public List<User> findAllUsers() {
        String sql = "SELECT * FROM user_profile";
        return jdbcTemplate.query(sql, new userMaper());
    }

    @Override
    public List<User> findAllUsersWithMoreInfo() {
        String sql = "SELECT up.*, photo.res, mui.phone, mui.date_birthday, mui.all_info FROM user_profile up " +
                        " left outer join user_photo photo on up.id = photo.user_id " +
                        " left outer join more_user_info mui on up.id = mui.user_id " + " ORDER BY up.id DESC ";
        return jdbcTemplate.query(sql, new userMaper(true));
    }

    @Override
    public User findMoreUserInfoById(int user_id) {
        String sql = "SELECT up.*, photo.res, mui.phone, mui.date_birthday, mui.all_info FROM user_profile up left outer join user_photo photo on up.id = photo.user_id left outer join more_user_info mui on up.id = mui.user_id WHERE up.id=?";
        return jdbcTemplate.queryForObject(sql, new userMaper(true),user_id);
    }

    @Override
    public List<User> searchMoreUserInfoByFIO(String fio) {
//        String sql = "SELECT up.*, photo.res, mui.phone, mui.date_birthday, mui.all_info FROM user_profile up left outer join user_photo photo on up.id = photo.user_id left outer join more_user_info mui on up.id = mui.user_id"
//                    + " WHERE up.LIKE = '" + fio + "'";
//        return jdbcTemplate.queryForObject(sql, new userMaper(true),user_id);
        return null;
    }


}
