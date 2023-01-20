package com.bcg.SeverMetropol.domain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
public class Task {

    private int id;
    private int user_id;
    private String name_task;
    private String status = "Новая";
    private String more_info;
    private String date_add;
    private String time_add;
    protected boolean use_it;
    protected boolean use_repair;
    protected boolean use_order;
    protected boolean use_product;
    protected boolean use_transport;
    private String users_array;
    private boolean send_only_select_users;
    private Document document;

    public Task() {
        setDateAndTime();
    }

    private void setDateAndTime(){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();

        date_add = dateFormat.format(now);
        time_add = timeFormat.format(now);
    }

}
