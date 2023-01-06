package com.bcg.SeverMetropol.domain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private int id;
    private int user_id;
    private String name_task;
    private String status;
    private String more_info;
    private String date_add;
    private String time_add;
    private boolean use_order;
    private boolean use_default;
    private Document document;

}
