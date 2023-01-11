package com.bcg.SeverMetropol.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class History {
    private int id;
    private int task_id;
    private String date_add;
    private String time_add;
    private String info;

    public History(int task_id, String info) {
        this.task_id = task_id;
        this.info = info;
    }
}
