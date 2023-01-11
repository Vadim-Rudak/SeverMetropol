package com.bcg.SeverMetropol.maper.task;

import com.bcg.SeverMetropol.domain.task.TaskOrder;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class orderTaskMaper implements RowMapper<TaskOrder> {

    @Override
    public TaskOrder mapRow(ResultSet rs, int i) throws SQLException {
        TaskOrder taskOrder = new TaskOrder();

        taskOrder.setId_task(rs.getInt("id_task"));
        taskOrder.setSend_to_user1(rs.getBoolean("send_to_user1"));
        taskOrder.setStatus_user1(rs.getBoolean("status_user1"));
        taskOrder.setSend_to_user2(rs.getBoolean("send_to_user2"));
        taskOrder.setStatus_user2(rs.getBoolean("status_user2"));
        taskOrder.setSend_to_user3(rs.getBoolean("send_to_user3"));
        taskOrder.setStatus_user3(rs.getBoolean("status_user3"));
        taskOrder.setSend_to_user4(rs.getBoolean("send_to_user4"));
        taskOrder.setStatus_user4(rs.getBoolean("status_user4"));
        taskOrder.setSend_to_user5(rs.getBoolean("send_to_user5"));
        taskOrder.setStatus_user5(rs.getBoolean("status_user5"));


        return taskOrder;
    }
}
