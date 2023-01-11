package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.History;
import com.bcg.SeverMetropol.maper.historyMaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class HistoryImpl implements HistoryRepo{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public HistoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(History history) {
        String sql = "INSERT INTO task_history (id, task_id, date_add, time_add, info) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, history.getId(),history.getTask_id(),history.getDate_add(),history.getTime_add(),history.getInfo());
    }

    @Override
    public List<History> findAllByTaskId(int task_id) {
        String sql = "SELECT * FROM task_history WHERE task_id=? ORDER BY id";
        return jdbcTemplate.query(sql,new historyMaper(),task_id);
    }

    @Override
    public int findLastId() {
        int lastId = 0;
        String sql = "SELECT MAX(id) FROM task_history";
        if(jdbcTemplate.queryForObject(sql,Integer.class)!=null){
            lastId = jdbcTemplate.queryForObject(sql,Integer.class);
        }
        return lastId;
    }
}
