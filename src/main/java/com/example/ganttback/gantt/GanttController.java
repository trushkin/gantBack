package com.example.ganttback.gantt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/gantt")
public class GanttController {
    @Autowired
    GanttChartService ganttChartService;

    @GetMapping("/{userId}")
    public GanttChart getChart(@PathVariable String userId) {
        return ganttChartService.fetchGanttChart(Long.parseLong(userId));
    }

    @PostMapping
    public void saveGanttChart(@RequestBody GanttChart ganttChart) {
        ganttChartService.saveGanttChart(ganttChart);
    }


}
