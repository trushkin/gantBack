package com.example.ganttback.resource;

import com.example.ganttback.gantt.task.GanttTaskRepository;
import com.example.ganttback.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceMapper {
    @Autowired
    ResourceRepository resourceRepository; //добавить userId
    @Autowired
    GanttTaskRepository ganttTaskRepository;
    @Autowired
    UserRepository userRepository;
    public ResourceDto toResourceDto(Resource resource){
        return new ResourceDtoBuilder()
                .setId(resource.getId())
                .setName(resource.getName())
                .setCapacity(resource.getCapacity())
                .setSalary(resource.getSalary())
                .setOccupancy(ganttTaskRepository.calculateOccupancy(resource.getId())) //TEMP
                .setTotalCost(ganttTaskRepository.calculateTotalResourceCost(resource.getId())) //TEMP
                .setUserId(resource.getUser().getId()) //TEMP
                .build();
    }
    public Resource toResourceEntity(ResourceDto resourceDto, Long userId){
        return new ResourceBuilder()
                .setName(resourceDto.getName())
                .setCapacity(resourceDto.getCapacity())
                .setSalary(resourceDto.getSalary())
                .setUser(userRepository.findById(userId).get())
                .build();
    }
}
