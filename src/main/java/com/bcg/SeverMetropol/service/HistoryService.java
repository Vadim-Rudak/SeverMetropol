package com.bcg.SeverMetropol.service;

import com.bcg.SeverMetropol.domain.History;
import com.bcg.SeverMetropol.repos.HistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class HistoryService implements HistoryServiceImpl{

    @Autowired
    private HistoryRepo historyRepo;

    @Override
    public void save(History history) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();

        history.setId(historyRepo.findLastId() + 1);
        history.setDate_add(dateFormat.format(now));
        history.setTime_add(timeFormat.format(now));

        historyRepo.save(history);

    }
}
