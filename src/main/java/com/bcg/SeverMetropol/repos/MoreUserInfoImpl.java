package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.MoreUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class MoreUserInfoImpl implements MoreUserInfoRepo {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public MoreUserInfoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void save(MoreUserInfo moreUserInfo) {
        String sql = "INSERT INTO more_user_info (id, user_id, date_birthday, phone, all_info) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, moreUserInfo.getId(), moreUserInfo.getUser_id(), moreUserInfo.getBirthday(), moreUserInfo.getPhone(), moreUserInfo.getAll_info());
    }

    @Override
    public void update(MoreUserInfo moreUserInfo) {
        String sql ="UPDATE more_user_info SET date_birthday=?,phone=?,all_info=? WHERE user_id=?";
        jdbcTemplate.update(sql,moreUserInfo.getBirthday(),moreUserInfo.getPhone(),moreUserInfo.getAll_info(),moreUserInfo.getUser_id());
    }

    @Override
    public void delete(int id_user_delete) {
        String sql = "DELETE FROM more_user_info WHERE id=?";
        jdbcTemplate.update(sql, id_user_delete);
    }

    @Override
    public int findLastId() {
        String sql = "SELECT MAX(id) FROM more_user_info";
        if (jdbcTemplate.queryForObject(sql,Integer.class)==null){
            return 0;
        }else{
            return jdbcTemplate.queryForObject(sql,Integer.class);
        }
    }
}
