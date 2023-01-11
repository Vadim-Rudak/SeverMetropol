package com.bcg.SeverMetropol.maper;

import com.bcg.SeverMetropol.domain.MoreUserInfo;
import com.bcg.SeverMetropol.domain.Photo;
import com.bcg.SeverMetropol.domain.User;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor
public class userMaper implements RowMapper<User> {

    private Boolean useMoreUserData = false;

    public userMaper(Boolean useMoreUserData) {
        this.useMoreUserData = useMoreUserData;
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setLast_name(rs.getString("last_name"));
        user.setPatronymic(rs.getString("patronymic"));
        user.setRole(rs.getString("role"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setActive(rs.getBoolean("active"));

        if (useMoreUserData){
            Photo user_photo = new Photo();
            user_photo.setRes(rs.getString("res"));
            user.setPhoto(user_photo);

            MoreUserInfo moreUserInfo = new MoreUserInfo();
            moreUserInfo.setPhone(rs.getString("phone"));
            moreUserInfo.setBirthday(rs.getString("date_birthday"));
            moreUserInfo.setAll_info(rs.getString("all_info"));
            user.setMoreUserInfo(moreUserInfo);
        }

        return user;
    }
}
