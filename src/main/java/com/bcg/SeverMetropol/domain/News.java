package com.bcg.SeverMetropol.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class News {

    private int id;
    private int id_user_created;
    private String date_created;
    private String time_created;
    private String href;
    private String titel;
    private String more_info;
    private NewsImg newsImg;
    private User user_created;
}
