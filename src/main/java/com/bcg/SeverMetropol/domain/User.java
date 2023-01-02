package com.bcg.SeverMetropol.domain;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private int id;
    private String name;
    private String last_name;
    private String patronymic;
    private String role;
    private String login;
    private String password;
    private boolean active;

    private Photo photo;
    private MoreUserInfo moreUserInfo;

    public User(String name, String last_name, String patronymic, String role, String login, String password, boolean active) {
        this.name = name;
        this.last_name = last_name;
        this.patronymic = patronymic;
        this.role = role;
        this.login = login;
        this.password = password;
        this.active = active;
    }
}
