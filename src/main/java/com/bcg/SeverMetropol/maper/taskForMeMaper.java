package com.bcg.SeverMetropol.maper;

import com.bcg.SeverMetropol.domain.task.Document;
import com.bcg.SeverMetropol.domain.task.TaskOrder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class taskForMeMaper implements RowMapper<TaskOrder> {

    @Override
    public TaskOrder mapRow(ResultSet rs, int i) throws SQLException {
        TaskOrder taskOrder = new TaskOrder();
        taskOrder.setId(rs.getInt("id"));
        taskOrder.setName_task(rs.getString("name_task"));
        taskOrder.setStatus(rs.getString("status"));
        taskOrder.setDate_add(rs.getString("date_add"));
        taskOrder.setTime_add(rs.getString("time_add"));

        try{
            taskOrder.setSend_to_user1(rs.getBoolean("send_to_user1"));
            taskOrder.setStatus_user1(rs.getBoolean("status_user1"));
        }catch (SQLException e){
            taskOrder.setSend_to_user1(false);
            taskOrder.setStatus_user1(false);
        }
        try{
            taskOrder.setSend_to_user2(rs.getBoolean("send_to_user2"));
            taskOrder.setStatus_user2(rs.getBoolean("status_user2"));
        }catch (SQLException e){
            taskOrder.setSend_to_user2(false);
            taskOrder.setStatus_user2(false);
        }
        try{
            taskOrder.setSend_to_user3(rs.getBoolean("send_to_user3"));
            taskOrder.setStatus_user3(rs.getBoolean("status_user3"));
        }catch (SQLException e){
            taskOrder.setSend_to_user3(false);
            taskOrder.setStatus_user3(false);
        }
        try{
            taskOrder.setSend_to_user4(rs.getBoolean("send_to_user4"));
            taskOrder.setStatus_user4(rs.getBoolean("status_user4"));
        }catch (SQLException e){
            taskOrder.setSend_to_user4(false);
            taskOrder.setStatus_user4(false);
        }
        try{
            taskOrder.setSend_to_user5(rs.getBoolean("send_to_user5"));
            taskOrder.setStatus_user5(rs.getBoolean("status_user5"));
        }catch (SQLException e){
            taskOrder.setSend_to_user5(false);
            taskOrder.setStatus_user5(false);
        }



        if (rs.getString("name_file")!=null){
            Document document = new Document();
            document.setName_file(rs.getString("name_file"));
            taskOrder.setDocument(document);
        }
        return taskOrder;
    }
}
