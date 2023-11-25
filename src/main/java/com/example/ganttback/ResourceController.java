package com.example.ganttback;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResourceController {
    @GetMapping("/resources")
    public List<Resource> getResources() {
        List<Resource> result = new ArrayList<>();
        result.add(new ResourceBuilder().setName("Ирина").setCapacity(10L).setSalary(15L).setOccupancy(5L).setTotalCost(75L).build());
        return result;
    }
}
