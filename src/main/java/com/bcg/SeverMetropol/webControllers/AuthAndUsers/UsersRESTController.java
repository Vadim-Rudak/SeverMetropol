package com.bcg.SeverMetropol.webControllers.AuthAndUsers;

import com.bcg.SeverMetropol.domain.Photo;
import com.bcg.SeverMetropol.domain.User;
import com.bcg.SeverMetropol.repos.UserRepo;
import com.bcg.SeverMetropol.webControllers.Menu.ToolBarUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UsersRESTController {

    @Autowired
    private UserRepo userRepo;


    @GetMapping(value = "/allUsersInJSON", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAllUsersInJSON() {
        return userRepo.findAllUsersWithMoreInfo();
    }

}
