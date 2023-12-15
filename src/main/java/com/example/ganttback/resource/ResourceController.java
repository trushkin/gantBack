package com.example.ganttback.resource;

import com.example.ganttback.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResourceController {
    @Autowired
    ResourceService resourceService;

    @GetMapping("/resources")
    public ApiResponse<List<ResourceDto>> getResources() {;
        return new ApiResponse<>(HttpStatus.OK.value(), "Resources fetched successfully", resourceService.getAllResources());
    }
}
