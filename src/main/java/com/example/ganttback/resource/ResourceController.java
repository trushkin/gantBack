package com.example.ganttback.resource;

import com.example.ganttback.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourceController {
    @Autowired
    ResourceService resourceService;

    @GetMapping("/{userId}")
    public ApiResponse<List<ResourceDto>> getResources(@PathVariable String userId) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Resources fetched successfully", resourceService.getAllResources(Long.parseLong(userId)));
    }
    @GetMapping("/resource-dropdown/{userId}")
    public ApiResponse<List<ResourceDropdownDto>> getResourcesToDropdown(@PathVariable String userId) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Resources fetched successfully", resourceService.getAllResourcesToDropdown(Long.parseLong(userId)));
    }
}
