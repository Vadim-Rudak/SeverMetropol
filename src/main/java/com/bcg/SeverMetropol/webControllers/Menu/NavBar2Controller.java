package com.bcg.SeverMetropol.webControllers.Menu;

import com.bcg.SeverMetropol.domain.Photo;
import com.bcg.SeverMetropol.domain.Tab;
import com.bcg.SeverMetropol.domain.User;
import com.bcg.SeverMetropol.repos.PhotoRepo;
import com.bcg.SeverMetropol.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class NavBar2Controller {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PhotoRepo photoRepo;

    @GetMapping("/navbar2-1")
    public String navBar2page1(Map<String, Object> model, Authentication authentication){

        User user = userRepo.findByLogin(authentication.getName());
        Photo photo = photoRepo.findUserPhoto(user.getId());

        model.putAll(ToolBarUserInfo.getUserMap(user,photo));

        return "Menu/NavBar2/menu_nav2_1";
    }

    @GetMapping("/navbar2-2")
    public String navBar2page2(Map<String, Object> model, Authentication authentication){

        User user = userRepo.findByLogin(authentication.getName());
        Photo photo = photoRepo.findUserPhoto(user.getId());

        model.putAll(ToolBarUserInfo.getUserMap(user,photo));

        return "Menu/NavBar2/menu_nav2_2";
    }

    @GetMapping("/navbar2-3")
    public String navBar2page3(Map<String, Object> model, Authentication authentication){

        User user = userRepo.findByLogin(authentication.getName());
        Photo photo = photoRepo.findUserPhoto(user.getId());

        model.putAll(ToolBarUserInfo.getUserMap(user,photo));

        return "Menu/NavBar2/menu_nav2_3";
    }

    @GetMapping("/navbar2-4")
    public String navBar2page4(Map<String, Object> model, Authentication authentication){

        User user = userRepo.findByLogin(authentication.getName());
        Photo photo = photoRepo.findUserPhoto(user.getId());

        model.putAll(ToolBarUserInfo.getUserMap(user,photo));

        return "Menu/NavBar2/menu_nav2_4";
    }

    @GetMapping("/navbar2-5")
    public String navBar2page5(Map<String, Object> model, Authentication authentication){

        User user = userRepo.findByLogin(authentication.getName());
        Photo photo = photoRepo.findUserPhoto(user.getId());

        model.putAll(ToolBarUserInfo.getUserMap(user,photo));

        return "Menu/NavBar2/menu_nav2_5";
    }


}
