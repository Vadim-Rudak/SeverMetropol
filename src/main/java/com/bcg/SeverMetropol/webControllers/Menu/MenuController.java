package com.bcg.SeverMetropol.webControllers.Menu;

import com.bcg.SeverMetropol.domain.*;
import com.bcg.SeverMetropol.repos.NewsImgRepo;
import com.bcg.SeverMetropol.repos.NewsRepo;
import com.bcg.SeverMetropol.repos.PhotoRepo;
import com.bcg.SeverMetropol.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class MenuController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PhotoRepo photoRepo;

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private NewsImgRepo newsImgRepo;

    @GetMapping("/")
    public String topMenu(Map<String, Object> model, Authentication authentication){

        User user = userRepo.findByLogin(authentication.getName());
        Photo photo = photoRepo.findUserPhoto(user.getId());

        model.putAll(ToolBarUserInfo.getUserMap(user,photo));

        return "Menu/menu";
    }

    @RequestMapping("/addNews")
    public String addNews(News news, @RequestParam(name="file_img", required=false, defaultValue="null") MultipartFile file_img) throws IOException {

        int id_add_news = newsRepo.findLastId() + 1;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        news.setId(id_add_news);
        news.setDate_created(dateFormat.format(now));
        news.setTime_created(timeFormat.format(now));
        newsRepo.save(news);

        if (!file_img.isEmpty()){
            NewsImg newsImg = new NewsImg();
            newsImg.setId(id_add_news);
            newsImg.setId_news(newsImgRepo.findLastId() + 1);
            newsImg.setRes(encodeString(file_img.getBytes()));
            newsImgRepo.save(newsImg);
        }

        return "redirect:/";
    }

    public static String encodeString(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

}
