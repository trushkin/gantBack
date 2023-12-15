package com.example.ganttback.gantt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class GanttController {
//    const data = {
//        data: [
//        { id: 1, text: 'Task #1', start_date: '2020-02-12', duration: 3, progress: 0.6 },
//        { id: 2, text: 'Task #2', start_date: '2020-02-16', duration: 3, progress: 0.4 }
//  ],
//        links: [
//        { id: 1, source: 1, target: 2, type: '0' }
//  ]
//    };
    @GetMapping(value = "/charts")
    public GanttChart getChart(){

        return new GanttChartBuilder()
                .addTask(1L, "Task #1", LocalDate.of(2023, 2, 12), 3L, 0.6)
                .addTask(2L, "Task #2", LocalDate.of(2023, 2, 16), 3L, 0.4)
                .addTask(3L, "Task #3", LocalDate.of(2023, 2, 18), 3L, 0.4)
                .addLink(1L, 2L, 1L, "0")
                .build();

    }

}
