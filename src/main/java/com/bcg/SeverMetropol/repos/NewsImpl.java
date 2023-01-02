package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.News;
import com.bcg.SeverMetropol.maper.newsMaper;
import com.bcg.SeverMetropol.maper.userMaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class NewsImpl implements NewsRepo {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public NewsImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(News news) {
        String sql = "INSERT INTO table_news (id, id_user_created, date_created, titel, more_info, time_created, href) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, news.getId(),news.getId_user_created(),news.getDate_created(),news.getTitel(),
        news.getMore_info(),news.getTime_created(),news.getHref());
    }

    @Override
    public int findLastId() {
        String sql = "SELECT MAX(id) FROM table_news";
        if (jdbcTemplate.queryForObject(sql,Integer.class)==null){
            return 0;
        }else{
            return jdbcTemplate.queryForObject(sql,Integer.class);
        }
    }

    @Override
    public List<News> getAll() {
        String sql = " SELECT news.*,img.res,usr.last_name,usr.name FROM table_news as news " +
                    " left outer join table_img_news as img on news.id = img.id_news " +
                    " left outer join user_profile as usr on news.id_user_created = usr.id ";
        return jdbcTemplate.query(sql, new newsMaper());
    }
}
