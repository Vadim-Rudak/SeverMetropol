package com.bcg.SeverMetropol.webControllers.Menu.myTask;


import com.bcg.SeverMetropol.domain.MoreUserInfo;
import com.bcg.SeverMetropol.domain.Photo;
import com.bcg.SeverMetropol.domain.User;
import com.bcg.SeverMetropol.domain.task.Document;
import com.bcg.SeverMetropol.domain.task.TaskOrder;
import com.bcg.SeverMetropol.repos.DocumentRepo;
import com.bcg.SeverMetropol.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Controller
public class MyTaskController {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private DocumentRepo documentRepo;


    @RequestMapping("/addNewOrderTask")
    public String addNewOrderTask(TaskOrder taskOrder, @RequestParam(name="doc_file", required=false, defaultValue="null") MultipartFile doc_file) throws IOException {

        int last_id = taskRepo.findLastId() + 1;
        taskOrder.setId(last_id);
        taskRepo.saveOrdTask(taskOrder);

        if (!doc_file.isEmpty()){
            Document document = new Document();
            document.setTask_id(last_id);
            document.setName_file(doc_file.getOriginalFilename());
            document.setType_file(doc_file.getContentType());
            document.setRes(encodeString(doc_file.getBytes()));
            documentRepo.save(document);
        }

        return "redirect:/navbar2-4";
    }

    public static String encodeString(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

}
