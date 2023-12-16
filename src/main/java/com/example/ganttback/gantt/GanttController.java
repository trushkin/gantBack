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

//        return new GanttChartBuilder()
//                .addTask(1L, "Task #1", LocalDate.of(2023, 2, 12), 3L, 0.6, "Василий", "Низкий")
//                .addTask(2L, "Task #2", LocalDate.of(2023, 2, 16), 3L, 0.4, "Василий", "Низкий")
//                .addTask(3L, "Task #3", LocalDate.of(2023, 2, 18), 3L, 0.4, "Василий", "Низкий")
//                .addLink(1L, 2L, 1L, 0L)
//                .build();
        return ganttChartService.fetchGanttChart(Long.parseLong(userId));

    }

    @PostMapping
    public void saveGanttChart(@RequestBody GanttChart ganttChart) {
        System.out.println(ganttChart.toString());
        ganttChartService.saveGanttChart(ganttChart);
    }


}
