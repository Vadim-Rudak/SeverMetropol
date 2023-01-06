package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.task.TaskOrder;
import com.bcg.SeverMetropol.maper.taskForMeMaper;
import com.bcg.SeverMetropol.maper.taskMaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class TaskImpl implements TaskRepo{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveOrdTask(TaskOrder taskOrder) {
        String sql = "INSERT INTO table_task (id, user_id, name_task, status, more_info,date_add,time_add, use_order, use_default) VALUES (?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, taskOrder.getId(), taskOrder.getUser_id(), taskOrder.getName_task(), taskOrder.getStatus(),
                taskOrder.getMore_info(), taskOrder.getDate_add(), taskOrder.getTime_add(), taskOrder.isUse_order(), taskOrder.isUse_default());
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

    @Override
    public List<TaskOrder> getAllOneUser(int user_id) {
        String sql = " select t1.id,t1.name_task,t1.date_add, t1.time_add, t1.status,ttu.*,ttf.name_file from table_task as t1 " +
                " left join table_task_user ttu on t1.id = ttu.id_task " +
                " left join table_task_files ttf on t1.id = ttf.task_id WHERE t1.user_id=? " + " ORDER BY t1.id DESC ";
        return jdbcTemplate.query(sql, new taskMaper(),user_id);
    }

    @Override
    public List<TaskOrder> getAllTasksForMe(String user_role) {
        String use_column;
        String column_status_check;
        switch (user_role) {
            case "Ревизор" -> {
                use_column = "send_to_user2";
                column_status_check = "status_user2";
            }
            case "Главный бухгалтер" -> {
                use_column = "send_to_user3";
                column_status_check = "status_user3";
            }
            case "Юрист" -> {
                use_column = "send_to_user4";
                column_status_check = "status_user4";
            }
            case "Директор" -> {
                use_column = "send_to_user5";
                column_status_check = "status_user5";
            }
            default -> {
                use_column = "send_to_user1";
                column_status_check = "status_user1";
            }
        }
        String sql = " select t1.id,t1.name_task,t1.date_add, t1.time_add, t1.status,ttu." + use_column + ",ttu." + column_status_check + ",ttf.name_file from table_task as t1 " +
                " left join table_task_user ttu on t1.id = ttu.id_task " +
                " left join table_task_files ttf on t1.id = ttf.task_id WHERE ttu." + use_column + " = true";
        return jdbcTemplate.query(sql, new taskForMeMaper());
    }

}
