package com.bcg.SeverMetropol.domain.task;

import lombok.*;

@Getter
@Setter
@ToString
public class TaskOrder extends Task{

    private boolean send_to_user1;
    private boolean status_user1;
    private boolean send_to_user2;
    private boolean status_user2;
    private boolean send_to_user3;
    private boolean status_user3;
    private boolean send_to_user4;
    private boolean status_user4;
    private boolean send_to_user5;
    private boolean status_user5;
    public TaskOrder(){
        super.setUse_order(true);
        super.setUse_default(false);
    }



}
