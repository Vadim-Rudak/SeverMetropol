package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.task.TaskOrder;

import java.util.List;

public interface TaskRepo {
    void saveOrdTask(TaskOrder taskOrder);
    int findLastId();

    List<TaskOrder> getAllOneUser(int user_id);

    List<TaskOrder> getAllTasksForMe(String user_role);
}
