package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.task.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DocumentImpl implements DocumentRepo{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public DocumentImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Document document) {
        String sql = "INSERT INTO table_task_files (task_id, name_file, type_file, res) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, document.getTask_id(), document.getName_file(), document.getType_file(), document.getRes());
    }
}
