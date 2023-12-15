package com.example.ganttback.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceMapper {
    @Autowired
    ResourceRepository resourceRepository; //добавить userId
    public ResourceDto toResourceDto(Resource resource){
        return new ResourceDtoBuilder()
                .setId(resource.getId())
                .setName(resource.getName())
                .setCapacity(resource.getCapacity())
                .setSalary(resource.getSalary())
                .setOccupancy(0L) //TEMP
                .setTotalCost(0L) //TEMP
                .build();
    }
}
