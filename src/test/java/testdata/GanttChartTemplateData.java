package testdata;

import com.example.ganttback.gantt.GanttChartBuilder;
import com.example.ganttback.gantt.link.GanttLink;
import com.example.ganttback.gantt.link.GanttLinkMapper;
import com.example.ganttback.gantt.task.GanttTask;
import com.example.ganttback.gantt.task.GanttTaskMapper;
import com.example.ganttback.resource.Resource;
import com.example.ganttback.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
public class GanttChartTemplateData {

    private static final GanttTaskMapper ganttTaskMapper = new GanttTaskMapper();
    private static final GanttLinkMapper ganttLinkMapper = new GanttLinkMapper();

    public static GanttChartBuilder chart1(){
        User testUser = UserTemplateData.user1();
        Resource resource1 = ResourceTemplateData.resource1().setUser(testUser).build();
        Resource resource2 = ResourceTemplateData.resource2().setUser(testUser).build();
        GanttTask task1 = GanttTaskTemplateData.task1().setUser(testUser).setOwner(resource1).build();
        GanttTask task1_1 = GanttTaskTemplateData.task1_1().setUser(testUser).setOwner(resource1).setParent(task1.getId()).build();
        GanttTask task2 = GanttTaskTemplateData.task2().setUser(testUser).setOwner(resource2).build();
        GanttLink link1 = new GanttLink(task1.getId(), task2.getId(), 1L, testUser);
        List<GanttTask> ganttTasksList = List.of(task1, task1_1, task2);
        List<GanttLink> ganttLinksList = List.of(link1);
        return new GanttChartBuilder()
                .setUserId(UserTemplateData.user1().getId())
                .setTasks(ganttTasksList.stream().map(ganttTaskMapper::toGanttChartDto).toList())
                .setLinks(ganttLinksList.stream().map(ganttLinkMapper::toGanttLinkDto).toList());
    }
}
