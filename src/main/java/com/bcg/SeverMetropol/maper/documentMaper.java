package com.bcg.SeverMetropol.maper;

import com.bcg.SeverMetropol.domain.task.Document;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor
public class documentMaper implements RowMapper<Document> {

    @Override
    public Document mapRow(ResultSet rs, int i) throws SQLException {
        Document document = new Document();
        document.setId(rs.getInt("id"));
        document.setTask_id(rs.getInt("task_id"));
        document.setName_file(rs.getString("name_file"));
        document.setType_file(rs.getString("type_file"));
        document.setRes(rs.getString("res"));
        return document;
    }
}
