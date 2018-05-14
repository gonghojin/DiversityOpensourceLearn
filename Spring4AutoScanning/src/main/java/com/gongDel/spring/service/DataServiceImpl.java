package com.gongDel.spring.service;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

@Service("dateService")
public class DataServiceImpl implements DateService {

    @Override
    public LocalDate getNextAssessmentDate() {
        return new LocalDate(2018, 10, 10);
    }
}
