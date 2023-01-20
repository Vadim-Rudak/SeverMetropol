package com.bcg.SeverMetropol.webControllers.Menu.taskForMe;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Controller
public class TaskForMeController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PhotoRepo photoRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private DocumentRepo documentRepo;

    @GetMapping("/navbar2-3")
    public String navBar2page3(Map<String, Object> model, Authentication authentication){

        User user = userRepo.findByLogin(authentication.getName());
        Photo photo = photoRepo.findUserPhoto(user.getId());

        model.putAll(ToolBarUserInfo.getUserMap(user,photo));
        model.put("listTaskForMe",taskRepo.getAllTasksForMe(user.getRole(), user.getId()));

        return "Menu/NavBar2/menu_nav2_3";
    }

    @PostMapping("/upDateOrderTaskStatus")
    public String upDateTaskForMeCheckBox(@RequestParam(name="task_id") int task_id,
                                          @RequestParam(name="user_role") String user_role,
                                          @RequestParam(name="check_status") String check_status) {

        TaskOrder taskOrder = taskRepo.findByTaskId(task_id);
        String historyInfo = "";

        switch (user_role) {
            case "Исполнитель" -> {
                taskOrder.setStatus_user1(Boolean.parseBoolean(check_status));
                historyInfo = Constans.HISTORY_USER1_OK;
            }
            case "Ревизор" -> {
                taskOrder.setStatus_user2(Boolean.parseBoolean(check_status));
                historyInfo = Constans.HISTORY_USER2_OK;
            }
            case "Главный бухгалтер" -> {
                taskOrder.setStatus_user3(Boolean.parseBoolean(check_status));
                historyInfo = Constans.HISTORY_USER3_OK;
            }
            case "Юрист" -> {
                taskOrder.setStatus_user4(Boolean.parseBoolean(check_status));
                historyInfo = Constans.HISTORY_USER4_OK;
            }
            case "Директор" -> {
                taskOrder.setStatus_user5(Boolean.parseBoolean(check_status));
                historyInfo = Constans.HISTORY_USER5_OK;
            }
        }
        taskRepo.upDateTask(taskOrder);

        historyService.save(new History(task_id,historyInfo));

        return "OK";
    }

    @RequestMapping(value = "/getFileInTask", method = RequestMethod.GET)
    public ResponseEntity<?> getFileInTask(@RequestParam(name="task_id", required=false, defaultValue="0") int task_id, Model model) {

        List<Document> listDocument = documentRepo.findByTaskId(task_id);

        if (listDocument.size() != 0){
            Document useDocument = listDocument.get(0);
            System.out.println("Тип файла => " + useDocument.getType_file());
            switch (useDocument.getType_file()){
                case "application/pdf"->{
                    return ResponseEntity.ok()
                            .contentType(MediaType.APPLICATION_PDF)
                            .body(Base64.getMimeDecoder().decode(useDocument.getRes()));
                }
//                case "application/vnd.openxmlformats-officedocument.wordprocessingml.document"->{
//                    return ResponseEntity.ok()
//                            .contentType(MediaType.APPLICATION_PDF)
//                            .body(Base64.getMimeDecoder().decode(useDocument.getRes()));
//                }
                default->{
                    return null;//next time to add Error not format file in web
                }
            }
        }else{
            return null;//next time to add Error not file
        }
    }

}