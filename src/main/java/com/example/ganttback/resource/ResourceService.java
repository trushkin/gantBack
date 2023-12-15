package com.example.ganttback.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceService {
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    ResourceMapper resourceMapper;

    public List<ResourceDto> getAllResources(Long userId){
        return resourceRepository.findAllByUserId(userId).stream().map(resourceMapper::toResourceDto).toList();
    }
    public List<ResourceDropdownDto> getAllResourcesToDropdown(Long userId){
        List<ResourceDto> resourceDtoList = resourceRepository.findAllByUserId(userId).stream().map(resourceMapper::toResourceDto).toList();
        List<ResourceDropdownDto> resourceDropdownDtoList = new ArrayList<>();
        for (ResourceDto temp: resourceDtoList) {
            resourceDropdownDtoList.add(new ResourceDropdownDto(temp.getName(), temp.getName()));
        }
        return resourceDropdownDtoList;
    }
    public void deleteResource(Long id){
        resourceRepository.deleteById(id);
    }
    public void saveResource(ResourceDto resourceDto){

    }
}
