package com.bcg.SeverMetropol.webControllers.Menu.myTask;


import com.bcg.SeverMetropol.constants.Constans;
import com.bcg.SeverMetropol.domain.History;
import com.bcg.SeverMetropol.domain.Photo;
import com.bcg.SeverMetropol.domain.User;
import com.bcg.SeverMetropol.domain.task.*;
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
import java.util.ArrayList;
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

        model.put("list_users",userRepo.findAllUsersWithMoreInfo());

        return "Menu/NavBar2/menu_nav2_4";
    }

    @RequestMapping("/addNewItTask")
    public String addNewItTask(TaskIT taskIT, @RequestParam(name="doc_file", required=false, defaultValue="null") MultipartFile doc_file) throws IOException {

        System.out.println("list->" + taskIT.getUsers_array());
        int last_id = taskRepo.findLastId() + 1;
        taskIT.setId(last_id);
        taskRepo.save(taskIT);

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

    @RequestMapping("/addNewRepairTask")
    public String addNewRepairTask(TaskRepair taskRepair, @RequestParam(name="doc_file", required=false, defaultValue="null") MultipartFile doc_file) throws IOException {

        int last_id = taskRepo.findLastId() + 1;
        taskRepair.setId(last_id);
        taskRepo.save(taskRepair);

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


    @RequestMapping("/addNewOrderTask")
    public String addNewOrderTask(TaskOrder taskOrder, @RequestParam(name="doc_file", required=false, defaultValue="null") MultipartFile doc_file) throws IOException {

        int last_id = taskRepo.findLastId() + 1;
        taskOrder.setId(last_id);
        taskRepo.save(taskOrder);

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

    @RequestMapping("/addNewProductTask")
    public String addNewProductTask(TaskProduct taskProduct, @RequestParam(name="doc_file", required=false, defaultValue="null") MultipartFile doc_file) throws IOException {

        int last_id = taskRepo.findLastId() + 1;
        taskProduct.setId(last_id);
        taskRepo.save(taskProduct);

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

    @RequestMapping("/addNewTransportTask")
    public String addNewTransportTask(TaskTransport taskTransport, @RequestParam(name="doc_file", required=false, defaultValue="null") MultipartFile doc_file) throws IOException {

        int last_id = taskRepo.findLastId() + 1;
        taskTransport.setId(last_id);
        taskRepo.save(taskTransport);

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



    public String encodeString(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

}
