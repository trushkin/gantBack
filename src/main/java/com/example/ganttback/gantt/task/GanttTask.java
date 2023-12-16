package com.example.ganttback.gantt.task;

import com.example.ganttback.resource.Resource;
import com.example.ganttback.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class GanttTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "text")
    private String text;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "priority")
    private String priority;
    @Column(name = "duration")
    private Long duration;
    @Column(name = "progress")
    private Double progress;
    @Column(name = "parent")
    private Long parent;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Resource owner;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Resource getOwner() {
        return owner;
    }

    public void setOwner(Resource owner) {
        this.owner = owner;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public GanttTask() {
    }

    public GanttTask(Long id, String text, LocalDate startDate, String priority, Long duration, Double progress, Long parent, Resource owner, User user) {
        this.id = id;
        this.text = text;
        this.startDate = startDate;
        this.priority = priority;
        this.duration = duration;
        this.progress = progress;
        this.parent = parent;
        this.owner = owner;
        this.user = user;
    }

    public GanttTask(Long id, String text, LocalDate startDate, String priority, Long duration, Double progress, Resource owner, User user) {
        this.id = id;
        this.text = text;
        this.startDate = startDate;
        this.priority = priority;
        this.duration = duration;
        this.progress = progress;
        this.owner = owner;
        this.user = user;
    }
}
