package com.example.ganttback.resource;

import com.example.ganttback.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PostMapping("/{userId}")
    public void saveResource(@PathVariable String userId, @RequestBody ResourceDto resourceDto){
        resourceService.saveResource(resourceDto, Long.parseLong(userId));
    }
    @DeleteMapping("/{resourceId}")
    public Long deleteResource(@PathVariable String resourceId){
        return resourceService.deleteResource(Long.parseLong(resourceId));
    }
    @PutMapping("/{resourceId}")
    public void updateResource(@RequestBody ResourceDto resourceDto){
        resourceService.updateResource(resourceDto);
    }
    @GetMapping("get-one/{resourceId}")
    public ApiResponse<ResourceDto> getResourceById(@PathVariable String resourceId){
        return new ApiResponse<>(HttpStatus.OK.value(), "Resource fetch successfully", resourceService.getResourceById(Long.parseLong(resourceId)));
    }

}
