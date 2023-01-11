package com.bcg.SeverMetropol.maper;

import com.bcg.SeverMetropol.domain.History;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor
public class historyMaper implements RowMapper<History> {
    @Override
    public History mapRow(ResultSet rs, int i) throws SQLException {
        History history = new History();
        history.setId(rs.getInt("id"));
        history.setTask_id(rs.getInt("task_id"));
        history.setDate_add(rs.getString("date_add"));
        history.setTime_add(rs.getString("time_add"));
        history.setInfo(rs.getString("info"));
        return history;
    }
}
