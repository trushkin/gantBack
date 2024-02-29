package com.example.ganttback.report;

import com.example.ganttback.gantt.task.GanttTaskRepository;
import com.example.ganttback.resource.ResourceDto;
import com.example.ganttback.resource.ResourceMapper;
import com.example.ganttback.resource.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    GanttTaskRepository ganttTaskRepository;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    ResourceMapper resourceMapper;

    public List<ReportDto> getReport(Long userId) {
        List<ReportDto> reportDtoList = new ArrayList<>();
        Long id = 0L;
        reportDtoList.add(new ReportDto(id++, "Требуемые временные затраты, дни", ganttTaskRepository.calculateTimeCosts(userId).toString()));
        Long projectTotalCost = 0L;
        for (ResourceDto temp : resourceRepository.findAllByUserId(userId).stream().map(resourceMapper::toResourceDto).toList()) {
            if (temp.getTotalCost() != null) {
                projectTotalCost += temp.getTotalCost();
            }
        }
        reportDtoList.add(new ReportDto(id++, "Денежные затраты, р.", projectTotalCost.toString()));
        reportDtoList.add(new ReportDto(id++, "Количество задач", ganttTaskRepository.calculateTasksQuantity(userId).toString()));
        reportDtoList.add(new ReportDto(id++, "Потраченное время, дни", ganttTaskRepository.calculateTimeSpent(userId).toString()));
        Double percentageDuration = ganttTaskRepository.calculateTimeSpent(userId).doubleValue() / ganttTaskRepository.calculateTimeCosts(userId) * 100;
        reportDtoList.add(new ReportDto(id++, "Процент готовности", String.format("%.0f", percentageDuration) + "%"));
        reportDtoList.add(new ReportDto(id++, "Дата начала проекта", ganttTaskRepository.findProjectStartDate(userId)));
        reportDtoList.add(new ReportDto(id++, "Дата окончания", ganttTaskRepository.findProjectEndDate(userId).replace("00:00:00.0", "")));




        return reportDtoList;
    }
}
