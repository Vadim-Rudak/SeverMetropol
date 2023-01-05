package com.bcg.SeverMetropol.domain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    private int id;
    private int task_id;
    private String name_file;
    private String type_file;
    private String res;
}
