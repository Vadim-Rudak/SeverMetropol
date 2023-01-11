package com.bcg.SeverMetropol.repos;

import com.bcg.SeverMetropol.domain.task.Document;
import java.util.List;

public interface DocumentRepo {

    void save(Document document);

    List<Document> findByTaskId(int task_id);

}
