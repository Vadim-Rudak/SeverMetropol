package com.bcg.SeverMetropol.domain.task;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TaskTransport extends Task{

    private int id_task;
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

    TaskTransport(){
        use_transport = true;
    }

}