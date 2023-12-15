package com.example.ganttback.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    ResourceMapper resourceMapper;
    public List<ResourceDto> getAllResources(){
        return resourceRepository.findAll().stream().map(resourceMapper::toResourceDto).toList();
    }
}
