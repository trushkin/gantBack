package com.example.ganttback.resource;

import com.example.ganttback.gantt.task.GanttTask;
import com.example.ganttback.gantt.task.GanttTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    ResourceMapper resourceMapper;
    @Autowired
    GanttTaskRepository ganttTaskRepository;

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
    public Long deleteResource(Long id){
        Optional<List<GanttTask>> allByOwnerId = ganttTaskRepository.findAllByOwnerId(id);

        if(!allByOwnerId.get().isEmpty()){
            return -1L;
        }
        resourceRepository.deleteById(id);
        return 1L;
    }
    public void saveResource(ResourceDto resourceDto, Long userId){
        resourceRepository.save(resourceMapper.toResourceEntity(resourceDto, userId));
    }
    public ResourceDto getResourceById(Long id){
        return resourceMapper.toResourceDto(resourceRepository.findById(id).get());
    }
    public void updateResource(ResourceDto resourceDto){
        Resource resource = resourceRepository.findById(resourceDto.getId()).get();
        resource.setName(resourceDto.getName());
        resource.setSalary(resourceDto.getSalary());
        resource.setCapacity(resourceDto.getCapacity());
        resourceRepository.save(resource);
    }
}
