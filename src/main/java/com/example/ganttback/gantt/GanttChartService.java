package com.example.ganttback.gantt;

import com.example.ganttback.gantt.link.GanttLink;
import com.example.ganttback.gantt.link.GanttLinkDto;
import com.example.ganttback.gantt.link.GanttLinkMapper;
import com.example.ganttback.gantt.link.GanttLinkRepository;
import com.example.ganttback.gantt.task.GanttTaskDto;
import com.example.ganttback.gantt.task.GanttTaskMapper;
import com.example.ganttback.gantt.task.GanttTaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class GanttChartService {
    @Autowired
    GanttLinkRepository ganttLinkRepository;
    @Autowired
    GanttTaskRepository ganttTaskRepository;
    @Autowired
    GanttTaskMapper ganttTaskMapper;
    @Autowired
    GanttLinkMapper ganttLinkMapper;

    public GanttChart fetchGanttChart(Long userId) {
        List<GanttTaskDto> ganttTaskDtoList = ganttTaskRepository.findAllByUserId(userId).stream().map(ganttTaskMapper::toGanttChartDto).toList();
        List<GanttLinkDto> ganttLinkDtoList = ganttLinkRepository.findAllByUserId(userId).stream().map(ganttLinkMapper::toGanttLinkDto).toList();
        GanttChart ganttChart = new GanttChartBuilder().setTasks(ganttTaskDtoList).setLinks(ganttLinkDtoList).setUserId(userId).build();
        return ganttChart;
    }

    @Transactional
    public void saveGanttChart(GanttChart ganttChart) {
        Map<Long, Long> taskIds = new HashMap<>(); //key - old id, value - new id
//        for (GanttTaskDto temp: ganttChart.getData()) {
//            taskIds.
//        }
        for (GanttTaskDto temp: ganttChart.getData()) {
            LocalDate correctDate = temp.getStartDate();
            temp.setStartDate(correctDate.plusDays(1));
        }
        ganttTaskRepository.deleteAllByUserId(ganttChart.getUserId());
        ganttTaskRepository.flush();
       // Long oldId;

//        List<GanttTaskDto> unsavedTasks = ganttChart.getData();
//        for (GanttTaskDto temp : ganttChart.getData()) {
//            if(temp.getParent() != null){
//                for (GanttTaskDto t: ganttChart.getData()) {
//                    if(t.getId().equals(temp.getParent())){
//                        Long newParentId = ganttTaskRepository.save(ganttTaskMapper.toGanttTaskEntity(t, ganttChart.getUserId())).getId();
//                        parentIds.put(t.getId(), newParentId);
//                        unsavedTasks.remove(t);
//                    }
//                }
//            }
//
//        }
        for (GanttTaskDto temp : ganttChart.getData()) {
           // oldId = temp.getId();
            if(temp.getParent() != null){
                Long newParentId = taskIds.get(temp.getParent());
                temp.setParent(newParentId);
            }
            Long newTaskId = ganttTaskRepository.save(ganttTaskMapper.toGanttTaskEntity(temp, ganttChart.getUserId())).getId();
            taskIds.put(temp.getId(), newTaskId);

        }
        for(GanttLinkDto temp : ganttChart.getLinks()){
            temp.setSource(taskIds.get(temp.getSource()));
            temp.setTarget(taskIds.get(temp.getTarget()));
        }
        ganttLinkRepository.deleteAllByUserId(ganttChart.getUserId());
        ganttLinkRepository.flush();
//        ganttTaskRepository.saveAllAndFlush(
//                ganttChart.getData()
//                        .stream()
//                        .map(ganttTask -> ganttTaskMapper.toGanttTaskEntity(ganttTask, ganttChart.getUserId()))
//                        .toList()
//        );
        ganttLinkRepository.saveAllAndFlush(
                ganttChart.getLinks()
                        .stream()
                        .map(link -> ganttLinkMapper.toGanttLinkEntity(link, ganttChart.getUserId()))
                        .toList()

        );
    }
}
