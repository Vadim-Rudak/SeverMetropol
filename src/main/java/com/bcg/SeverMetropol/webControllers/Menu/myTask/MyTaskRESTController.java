package com.bcg.SeverMetropol.webControllers.Menu.myTask;

import com.bcg.SeverMetropol.repos.HistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyTaskRESTController {

    @Autowired
    private HistoryRepo historyRepo;


    @GetMapping(value = "/taskHistory", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getTaskHistory(@RequestParam(name="task_id", required=false, defaultValue="0") int task_id) {
        return historyRepo.findAllByTaskId(task_id);
    }

}
