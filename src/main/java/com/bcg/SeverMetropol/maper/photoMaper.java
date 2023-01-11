package com.bcg.SeverMetropol.maper;

import com.bcg.SeverMetropol.domain.Photo;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class photoMaper implements RowMapper<Photo> {

    @Override
    public Photo mapRow(ResultSet rs, int rowNum) throws SQLException {

        Photo photo = new Photo();
        photo.setId(rs.getInt("id"));
        photo.setUser_id(rs.getInt("user_id"));
        photo.setRes(rs.getString("res"));

        return photo;
    }
}
