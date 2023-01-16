package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.task.Task;
import com.bcg.SeverMetropol.domain.task.TaskOrder;

import java.util.List;

public interface TaskRepo {

    void save(Task task);

    int findLastId();

    void upDateTask(TaskOrder taskOrder);
    TaskOrder findByTaskId(int id_task);

    List<TaskOrder> getAllOneUser(int user_id);

    List<TaskOrder> getAllTasksForMe(String user_role);
}
