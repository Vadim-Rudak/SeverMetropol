package com.bcg.SeverMetropol.domain.task;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TaskIT extends Task{

    public TaskIT() {
        use_it = true;
    }
}
