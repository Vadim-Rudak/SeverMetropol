package com.bcg.SeverMetropol.webControllers.Menu.myTask;


import com.bcg.SeverMetropol.constants.Constans;
import com.bcg.SeverMetropol.domain.History;
import com.bcg.SeverMetropol.domain.Photo;
import com.bcg.SeverMetropol.domain.User;
import com.bcg.SeverMetropol.domain.task.Document;
import com.bcg.SeverMetropol.domain.task.TaskOrder;
import com.bcg.SeverMetropol.repos.DocumentRepo;
import com.bcg.SeverMetropol.repos.PhotoRepo;
import com.bcg.SeverMetropol.repos.TaskRepo;
import com.bcg.SeverMetropol.repos.UserRepo;
import com.bcg.SeverMetropol.service.HistoryService;
import com.bcg.SeverMetropol.webControllers.Menu.ToolBarUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Controller
public class MyTaskController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PhotoRepo photoRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private DocumentRepo documentRepo;

    @Autowired
    private HistoryService historyService;


    @GetMapping("/navbar2-4")
    public String navBar2page4(Map<String, Object> model, Authentication authentication){

        User user = userRepo.findByLogin(authentication.getName());
        Photo photo = photoRepo.findUserPhoto(user.getId());

        model.putAll(ToolBarUserInfo.getUserMap(user,photo));

        List<TaskOrder> list_my_task = taskRepo.getAllOneUser(user.getId());
        model.put("MyTasks",list_my_task);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        model.put("date_now",dateFormat.format(now));

        return "Menu/NavBar2/menu_nav2_4";
    }


    @RequestMapping("/addNewOrderTask")
    public String addNewOrderTask(TaskOrder taskOrder, @RequestParam(name="doc_file", required=false, defaultValue="null") MultipartFile doc_file) throws IOException {

        int last_id = taskRepo.findLastId() + 1;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        taskOrder.setId(last_id);
        taskOrder.setDate_add(dateFormat.format(now));
        taskOrder.setTime_add(timeFormat.format(now));
        taskRepo.saveOrdTask(taskOrder);

        if (!doc_file.isEmpty()){
            Document document = new Document();
            document.setTask_id(last_id);
            document.setName_file(doc_file.getOriginalFilename());
            document.setType_file(doc_file.getContentType());
            document.setRes(encodeString(doc_file.getBytes()));
            documentRepo.save(document);
        }

        historyService.save(new History(last_id, Constans.HISTORY_ADD));

        return "redirect:/navbar2-4";
    }

    public static String encodeString(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

}
