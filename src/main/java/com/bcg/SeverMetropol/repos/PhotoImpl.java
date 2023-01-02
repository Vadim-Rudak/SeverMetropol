package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.Photo;
import com.bcg.SeverMetropol.maper.photoMaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class PhotoImpl implements PhotoRepo{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public PhotoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void save(Photo photo) {
        String sql = "INSERT INTO user_photo (id, user_id, res) VALUES (?,?,?)";
        jdbcTemplate.update(sql, photo.getId(), photo.getUser_id(), photo.getRes());
    }


    @Override
    public void updatePhoto(Photo photo) {
        String sql ="UPDATE user_photo SET res=? WHERE user_id=?";
        jdbcTemplate.update(sql,photo.getRes(),photo.getUser_id());
    }

    @Override
    public int findLastPhotoId() {
        String sql = "SELECT MAX(id) FROM user_photo";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public Photo findUserPhoto(int user_id) {
        String sql = "SELECT * FROM user_photo WHERE user_id=?";
        List<Photo> list = jdbcTemplate.query(sql,new photoMaper(),user_id);
        if (list.size()!=0){
            return jdbcTemplate.queryForObject(sql,new photoMaper(),user_id);
        }else{
           return null;
        }
    }
}
