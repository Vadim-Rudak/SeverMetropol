package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.task.*;
import com.bcg.SeverMetropol.maper.task.orderTaskMaper;
import com.bcg.SeverMetropol.maper.task.taskForMeMaper;
import com.bcg.SeverMetropol.maper.task.taskMaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class TaskImpl implements TaskRepo{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Task task) {

        String sql = "INSERT INTO table_task (id, user_id, name_task, status, more_info,date_add,time_add,use_it,use_repair,use_order,use_product,use_transport,users_array) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, task.getId(), task.getUser_id(), task.getName_task(), task.getStatus(),
                task.getMore_info(), task.getDate_add(), task.getTime_add(), task.isUse_it(), task.isUse_repair(), task.isUse_order(), task.isUse_product(), task.isUse_transport(), task.getUsers_array());

        if (task instanceof TaskIT){
            System.out.println("Сохранение заявки IT");

        }else if(task instanceof TaskRepair){
            System.out.println("Сохранение заявки ремонтной службы");

        }else if(task instanceof TaskOrder){
            System.out.println("Сохранение заявки концелярии");
            TaskOrder taskOrder = (TaskOrder) task;
            String sql2 = "INSERT INTO table_task_user (id_task, send_to_user1, send_to_user2, send_to_user3, send_to_user4, send_to_user5," +
                    "status_user1, status_user2, status_user3, status_user4, status_user5) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql2, taskOrder.getId(), taskOrder.isSend_to_user1(), taskOrder.isSend_to_user2(), taskOrder.isSend_to_user3(),
                    taskOrder.isSend_to_user4(),taskOrder.isSend_to_user5(), taskOrder.isStatus_user1(), taskOrder.isStatus_user2(),
                    taskOrder.isStatus_user3(), taskOrder.isStatus_user4(), taskOrder.isStatus_user5());

        }else if(task instanceof TaskProduct){
            System.out.println("Сохранение заявки продукции");

        }else if (task instanceof TaskTransport){
            System.out.println("Сохранение заявки корп. транспорта");

        }
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
    public void upDateTask(TaskOrder taskOrder) {
        String sql ="UPDATE table_task_user SET status_user1=?,status_user2=?,status_user3=?,status_user4=?,status_user5=? WHERE id_task=?";
        jdbcTemplate.update(sql, taskOrder.isStatus_user1(), taskOrder.isStatus_user2(), taskOrder.isStatus_user3(),
                taskOrder.isStatus_user4(),taskOrder.isStatus_user5(),taskOrder.getId_task());
    }

    @Override
    public TaskOrder findByTaskId(int id_task) {
        String sql = "SELECT * FROM table_task_user WHERE id_task=?";
        List<TaskOrder> list = jdbcTemplate.query(sql, new orderTaskMaper(), id_task);
        if(list.size()==0){
            return null;
        }else{
            return jdbcTemplate.queryForObject(sql, new orderTaskMaper(), id_task);
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
        boolean use_role = true;
        List<TaskOrder> list_all_tasks;
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
                use_role = false;
                use_column = "send_to_user1";
                column_status_check = "status_user1";
            }
        }
        String sql = " select t1.id,t1.name_task,t1.date_add, t1.time_add, t1.status,ttu." + use_column + ",ttu." + column_status_check + ",ttf.name_file from table_task as t1 " +
                " left join table_task_user ttu on t1.id = ttu.id_task " +
                " left join table_task_files ttf on t1.id = ttf.task_id WHERE ttu." + use_column + " = true";

        if(use_role){
            list_all_tasks = jdbcTemplate.query(sql, new taskForMeMaper());
        }else{
            list_all_tasks = new ArrayList<>();
        }

        return list_all_tasks;
    }

}
