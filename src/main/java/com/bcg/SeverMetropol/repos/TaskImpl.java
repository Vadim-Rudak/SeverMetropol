package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.task.TaskOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class TaskImpl implements TaskRepo{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveOrdTask(TaskOrder taskOrder) {
        String sql = "INSERT INTO table_task (id, user_id, name_task, more_info, use_order, use_default) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, taskOrder.getId(), taskOrder.getUser_id(), taskOrder.getName_task(),
                taskOrder.getMore_info(), taskOrder.isUse_order(), taskOrder.isUse_default());
        String sql2 = "INSERT INTO table_task_user (id_task, send_to_user1, send_to_user2, send_to_user3, send_to_user4, send_to_user5," +
                "status_user1, status_user2, status_user3, status_user4, status_user5) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql2, taskOrder.getId(), taskOrder.isSend_to_user1(), taskOrder.isSend_to_user2(), taskOrder.isSend_to_user3(),
                taskOrder.isSend_to_user4(),taskOrder.isSend_to_user5(), taskOrder.isStatus_user1(), taskOrder.isStatus_user2(),
                taskOrder.isStatus_user3(), taskOrder.isStatus_user4(), taskOrder.isStatus_user5());

    }

    @Override
    public int findLastId() {
        String sql = "SELECT MAX(id) FROM table_task";
        if (jdbcTemplate.queryForObject(sql,Integer.class)==null){
            return 0;
        }else{
            return jdbcTemplate.queryForObject(sql,Integer.class);
        }
    }
}
