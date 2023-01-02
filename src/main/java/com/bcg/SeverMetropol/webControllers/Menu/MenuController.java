package com.bcg.SeverMetropol.webControllers.Menu;

import com.bcg.SeverMetropol.domain.Photo;
import com.bcg.SeverMetropol.domain.Tab;
import com.bcg.SeverMetropol.domain.User;
import com.bcg.SeverMetropol.repos.PhotoRepo;
import com.bcg.SeverMetropol.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MenuController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PhotoRepo photoRepo;

    @GetMapping("/")
    public String topMenu(Map<String, Object> model, Authentication authentication){

        User user = userRepo.findByLogin(authentication.getName());
        Photo photo = photoRepo.findUserPhoto(user.getId());

        model.putAll(ToolBarUserInfo.getUserMap(user,photo));

        return "Menu/menu";
    }

}
