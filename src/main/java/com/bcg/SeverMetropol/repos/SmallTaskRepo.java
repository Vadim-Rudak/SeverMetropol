package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.task.SmallTask;
import com.bcg.SeverMetropol.domain.task.Task;

import java.util.List;

public interface SmallTaskRepo {
    void save(SmallTask smallTask);
}
