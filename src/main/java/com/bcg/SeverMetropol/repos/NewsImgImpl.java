package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.NewsImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class NewsImgImpl implements NewsImgRepo {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public NewsImgImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void save(NewsImg newsImg) {
        String sql = "INSERT INTO table_img_news (id, id_news, res) VALUES (?,?,?)";
        jdbcTemplate.update(sql, newsImg.getId(),newsImg.getId_news(),newsImg.getRes());
    }

    @Override
    public int findLastId() {
        String sql = "SELECT MAX(id) FROM table_img_news";
        if (jdbcTemplate.queryForObject(sql,Integer.class)==null){
            return 0;
        }else{
            return jdbcTemplate.queryForObject(sql,Integer.class);
        }
    }
}
