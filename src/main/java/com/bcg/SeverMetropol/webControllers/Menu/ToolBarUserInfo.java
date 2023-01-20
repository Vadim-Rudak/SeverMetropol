package com.bcg.SeverMetropol.webControllers.Menu;

import com.bcg.SeverMetropol.domain.Photo;
import com.bcg.SeverMetropol.domain.Tab;
import com.bcg.SeverMetropol.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
*
* Сontrol of displayed tabs for the user.
*
* */

public class ToolBarUserInfo {

    public static Map<String, Object> getUserMap(User user, Photo photo){
        Map<String, Object> model = new HashMap<>();

        if (photo == null){
            model.put("first_char",user.getName().charAt(0));
            model.put("use_img",false);
            model.put("use_default_img",true);

        }else{
            model.put("use_img",true);
            model.put("use_default_img",false);
            model.put("user_photo",photo.getRes());
        }
        model.put("AuthUser",user);
        model.put("role",user.getRole());

        ArrayList<Tab> tabs = new ArrayList<>();
        boolean visible_btn_add_news = false;

        switch (user.getRole()){
            case "Ревизор" -> {
                tabs.add(new Tab("СЕРВИСЫ","/",false));
                tabs.add(new Tab("СОТРУДНИКИ","/listAllUsers",false));
                tabs.add(new Tab("СОТРУДНИКАМ","/",false));
                tabs.add(new Tab("ПОДРАЗДЕЛЕНИЯ","/",false));
                tabs.add(new Tab("НОВОСТИ","/",false));
            }
            case "Главный бухгалтер" -> {
                tabs.add(new Tab("СОТРУДНИКАМ","/",false));
                tabs.add(new Tab("НОВОСТИ","/",false));
                tabs.add(new Tab("АДМИНИСТРИРОВАНИЕ","/",false));

            }
            case "Юрист" -> {
                tabs.add(new Tab("СОТРУДНИКАМ","/",false));
            }
            case "Директор","Admin" -> {
                visible_btn_add_news = true;
                tabs.add(new Tab("СЕРВИСЫ","/",false));
                tabs.add(new Tab("СОТРУДНИКИ","/listAllUsers",false));
                tabs.add(new Tab("СОТРУДНИКАМ","/",false));
                tabs.add(new Tab("ПОДРАЗДЕЛЕНИЯ","/",false));
                tabs.add(new Tab("НОВОСТИ","/",false));
                tabs.add(new Tab("АДМИНИСТРИРОВАНИЕ","/",false));
            }
            default -> {
                tabs.add(new Tab("СЕРВИСЫ","/",false));
                tabs.add(new Tab("СОТРУДНИКАМ","/",false));
                tabs.add(new Tab("ПОДРАЗДЕЛЕНИЯ","/",false));
                tabs.add(new Tab("НОВОСТИ","/",false));
                tabs.add(new Tab("АДМИНИСТРИРОВАНИЕ","/",false));
            }
        }

        model.put("tabs",tabs);
        model.put("visible_btn_add_news",visible_btn_add_news);

        return model;
    }
}
