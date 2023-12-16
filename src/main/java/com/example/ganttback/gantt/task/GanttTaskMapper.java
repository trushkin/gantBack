package com.example.ganttback.gantt.task;

import com.example.ganttback.resource.ResourceRepository;
import com.example.ganttback.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GanttTaskMapper {
    @Autowired
    GanttTaskRepository ganttTaskRepository;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    UserRepository userRepository;
    public GanttTask toGanttTaskEntity(GanttTaskDto ganttTaskDto, Long userId){
        return new GanttTaskBuilder()
                .setId(ganttTaskDto.getId())
                .setText(ganttTaskDto.getText())
                .setStartDate(ganttTaskDto.getStartDate())
                .setPriority(ganttTaskDto.getPriority())
                .setDuration(ganttTaskDto.getDuration())
                .setProgress(ganttTaskDto.getProgress())
                .setOwner(resourceRepository.findByName(ganttTaskDto.getResource()))
                .setUser(userRepository.findById(userId).get())
                .setParent(ganttTaskDto.getParent())
                .build();
    }
    public GanttTaskDto toGanttChartDto(GanttTask ganttTask){
        return new GanttTaskDtoBuilder()
                .setId(ganttTask.getId())
                .setText(ganttTask.getText())
                .setStartDate(ganttTask.getStartDate())
                .setDuration(ganttTask.getDuration())
                .setProgress(ganttTask.getProgress())
                .setResource(ganttTask.getOwner().getName())
                .setPriority(ganttTask.getPriority())
                .setUserId(ganttTask.getUser().getId())
                .setParent(ganttTask.getParent())
                .build();//no user id
    }
}
