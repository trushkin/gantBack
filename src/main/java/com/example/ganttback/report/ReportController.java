package com.example.ganttback.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/report")
public class ReportController {
    @Autowired ReportService reportService;
    @GetMapping("/{userId}")
    public List<ReportDto> getReport(@PathVariable String userId){
//        List<ReportDto> reportDtoList = new ArrayList<>();
//        reportDtoList.add(new ReportDto(1L, "name1", "value1"));
//        reportDtoList.add(new ReportDto(2L,"name2", "value2"));
//        reportDtoList.add(new ReportDto(3L,"name3", "value3"));
//        return reportDtoList;
        return reportService.getReport(Long.parseLong(userId));
    }
}
