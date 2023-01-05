package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.task.TaskOrder;

public interface TaskRepo {
    void saveOrdTask(TaskOrder taskOrder);
    int findLastId();
}
