package com.bcg.SeverMetropol.webControllers.AuthAndUsers;

import com.bcg.SeverMetropol.domain.MoreUserInfo;
import com.bcg.SeverMetropol.domain.Photo;
import com.bcg.SeverMetropol.domain.User;
import com.bcg.SeverMetropol.repos.MoreUserInfoRepo;
import com.bcg.SeverMetropol.repos.PhotoRepo;
import com.bcg.SeverMetropol.repos.UserRepo;
import com.bcg.SeverMetropol.webControllers.Menu.ToolBarUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Controller
public class UsersController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PhotoRepo photoRepo;

    @Autowired
    private MoreUserInfoRepo moreUserInfoRepo;

    @GetMapping("/listAllUsers")
    public String pageAllUsers(Map<String, Object> model, Authentication authentication){

        User user = userRepo.findByLogin(authentication.getName());
        Photo photo = photoRepo.findUserPhoto(user.getId());
        model.putAll(ToolBarUserInfo.getUserMap(user,photo));

        Iterable<User> list_users = userRepo.findAllUsersWithMoreInfo();
        model.put("listUsers", list_users);

        return "UsersProfiles/AllUsersProfile";
    }

    @GetMapping("/UserProfile")
    public String pageOneUser(@RequestParam(name="id") int id, Map<String, Object> model, Authentication authentication){

        User user = userRepo.findByLogin(authentication.getName());
        Photo photo = photoRepo.findUserPhoto(user.getId());
        model.putAll(ToolBarUserInfo.getUserMap(user,photo));

        //See user with all info
        User user_more_info = userRepo.findMoreUserInfoById(id);
        model.put("seeUser",user_more_info);

        return "UsersProfiles/UserProfile";
    }

    @GetMapping("/EditUserProfile")
    public String editUserProfile(@RequestParam(name="id") int id, Map<String, Object> model, Authentication authentication){

        User user = userRepo.findByLogin(authentication.getName());
        Photo photo = photoRepo.findUserPhoto(user.getId());
        model.putAll(ToolBarUserInfo.getUserMap(user,photo));

        User user_more_info = userRepo.findMoreUserInfoById(id);
        model.put("seeUser",user_more_info);
        model.put("user_id",id);


        return "UsersProfiles/EditUserProfile";
    }


    @PostMapping("/upDateActiveProfile")
    public String upDateActiveProfile(@RequestParam(name="user_id") int user_id,@RequestParam(name="active_status") boolean active_status) {

        User user = userRepo.findUserById(user_id);
        user.setActive(active_status);
        userRepo.upDate(user);

        return "OK";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam(name="user_id") int user_id) {

        userRepo.deleteUserById(user_id);

        return "redirect:/listAllUsers";
    }

    @RequestMapping("/addNewUser")
    public String addNewUser(User user,@RequestParam(name="file_user_photo", required=false, defaultValue="null") MultipartFile file_photo) throws IOException {

        user.setId(userRepo.findLastId() + 1);
        user.setActive(true);
        userRepo.save(user);

//        System.out.println("FD" + file_photo.getResource().getFilename());

        if (!file_photo.isEmpty()){
            Photo photo = new Photo();
            photo.setId(photoRepo.findLastPhotoId() + 1);
            photo.setUser_id(user.getId());
            photo.setRes(encodeString(file_photo.getBytes()));
            photoRepo.save(photo);
        }


        MoreUserInfo moreUserInfo = user.getMoreUserInfo();
        moreUserInfo.setId(moreUserInfoRepo.findLastId() + 1);
        moreUserInfo.setUser_id(user.getId());
        moreUserInfoRepo.save(moreUserInfo);

        return "redirect:/listAllUsers";
    }

    @RequestMapping("/updateUser")
    public String updateUser(User user,@RequestParam(name="file_user_photo", required=false, defaultValue="null") MultipartFile file_photo) throws IOException {

        System.out.println(user.getId());
        userRepo.upDate(user);

        MoreUserInfo moreUserInfo = user.getMoreUserInfo();
        moreUserInfo.setUser_id(user.getId());
        moreUserInfoRepo.update(user.getMoreUserInfo());

        System.out.println("Файл" + file_photo.isEmpty());
        if (!file_photo.isEmpty()){
            Photo photo = photoRepo.findUserPhoto(user.getId());

            if (photo == null){
                photo = new Photo();
                photo.setId(photoRepo.findLastPhotoId() + 1);
                photo.setUser_id(user.getId());
                photo.setRes(encodeString(file_photo.getBytes()));
                photoRepo.save(photo);
            }else{
                photo.setUser_id(user.getId());
                photo.setRes(encodeString(file_photo.getBytes()));
                photoRepo.updatePhoto(photo);
            }


        }

        return "redirect:/UserProfile?id=" + user.getId();
    }

    @RequestMapping(value = "/getUserPhoto", method = RequestMethod.GET)
    public ResponseEntity<?> getImage(@RequestParam(name="user_id", required=false, defaultValue="0") int user_id) {
        if(user_id!=0&&photoRepo.findUserPhoto(user_id)!=null){
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(Base64.getMimeDecoder().decode(photoRepo.findUserPhoto(user_id).getRes()));
        }else{
            return null;
        }
    }




    public static String encodeString(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

}
