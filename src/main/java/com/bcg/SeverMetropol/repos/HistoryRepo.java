package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.History;

import java.util.List;

public interface HistoryRepo {
    void save(History history);
    List<History> findAllByTaskId(int task_id);

    int findLastId();
}
