package com.example.ganttback.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/report")
public class ReportController {
    @Autowired ReportService reportService;
    @GetMapping("/{userId}")
    public List<ReportDto> getReport(@PathVariable String userId){
        return reportService.getReport(Long.parseLong(userId));
    }
}
