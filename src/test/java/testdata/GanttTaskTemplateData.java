package testdata;

import com.example.ganttback.gantt.task.GanttTaskBuilder;

import java.time.LocalDate;

public class GanttTaskTemplateData {
    public static GanttTaskBuilder task1(){
        return new GanttTaskBuilder()
                .setDuration(10L)
                .setPriority("High")
                .setProgress(0.7)
                .setText("task #1")
                .setStartDate(LocalDate.of(2024, 2, 25));
    }
    public static GanttTaskBuilder task1_1(){
        return new GanttTaskBuilder()
                .setDuration(2L)
                .setPriority("Middle")
                .setProgress(0.2)
                .setText("task #1_1")
                .setStartDate(LocalDate.of(2024, 2, 26));
    }
    public static GanttTaskBuilder task2(){
        return new GanttTaskBuilder()
                .setDuration(5L)
                .setPriority("Low")
                .setProgress(0.4)
                .setText("task #2")
                .setStartDate(LocalDate.of(2024, 3, 8));
    }
    
}
