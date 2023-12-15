package com.example.ganttback.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResourceController {
    @Autowired
    ResourceService resourceService;
    @GetMapping("/resources")
    public List<ResourceDto> getResources() {
//        List<ResourceDto> result = new ArrayList<>();
//        result.add(new ResourceDtoBuilder().setId(1L).setName("Ирина").setCapacity(10L).setSalary(15L).setOccupancy(5L).setTotalCost(75L).build());
        List<ResourceDto> resourceDtoList = resourceService.getAllResources();
        return resourceDtoList;
    }
}
