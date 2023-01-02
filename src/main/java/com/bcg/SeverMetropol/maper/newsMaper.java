package com.bcg.SeverMetropol.maper;

import com.bcg.SeverMetropol.domain.*;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor
public class newsMaper implements RowMapper<News> {

    @Override
    public News mapRow(ResultSet rs, int rowNum) throws SQLException {
        News news = new News();
        news.setId(rs.getInt("id"));
        news.setId_user_created(rs.getInt("id_user_created"));
        news.setDate_created(rs.getString("date_created"));
        news.setTitel(rs.getString("titel"));
        news.setMore_info(rs.getString("more_info"));
        news.setTime_created(rs.getString("time_created"));
        news.setHref(rs.getString("href"));

        if (rs.getString("res")!=null){
            NewsImg newsImg = new NewsImg();
            newsImg.setRes(rs.getString("res"));
            news.setNewsImg(newsImg);
        }

        User user_create = new User();
        user_create.setLast_name(rs.getString("last_name"));
        user_create.setName(rs.getString("name"));
        news.setUser_created(user_create);

        return news;
    }
}
