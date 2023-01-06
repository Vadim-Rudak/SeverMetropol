package com.bcg.SeverMetropol.webControllers.Menu.taskForMe;

import com.bcg.SeverMetropol.domain.Photo;
import com.bcg.SeverMetropol.domain.User;
import com.bcg.SeverMetropol.repos.PhotoRepo;
import com.bcg.SeverMetropol.repos.TaskRepo;
import com.bcg.SeverMetropol.repos.UserRepo;
import com.bcg.SeverMetropol.webControllers.Menu.ToolBarUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;

@Controller
public class TaskForMeController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PhotoRepo photoRepo;

    @Autowired
    private TaskRepo taskRepo;

    @GetMapping("/navbar2-3")
    public String navBar2page3(Map<String, Object> model, Authentication authentication){

        User user = userRepo.findByLogin(authentication.getName());
        Photo photo = photoRepo.findUserPhoto(user.getId());

        model.putAll(ToolBarUserInfo.getUserMap(user,photo));
        model.put("listTaskForMe",taskRepo.getAllTasksForMe(user.getRole()));

        return "Menu/NavBar2/menu_nav2_3";
    }
}
