package com.example.ganttback.gantt.link;

import com.example.ganttback.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GanttLinkMapper {
    @Autowired
    GanttLinkRepository ganttLinkRepository;
    @Autowired
    UserRepository userRepository;

    public GanttLink toGanttLinkEntity(GanttLinkDto ganttLinkDto, Long userId) {
        return new GanttLinkBuilder()
                .setId(ganttLinkDto.getId())
                .setSource(ganttLinkDto.getSource())
                .setTarget(ganttLinkDto.getTarget())
                .setType(ganttLinkDto.getType())
                .setUser(userRepository.findById(userId).get())
                .build();
    }

    public GanttLinkDto toGanttLinkDto(GanttLink ganttLink) {
        return new GanttLinkDtoBuilder()
                .setId(ganttLink.getId())
                .setSource(ganttLink.getSource())
                .setTarget(ganttLink.getTarget())
                .setType(ganttLink.getType())
                .build();//no user id
    }
}
