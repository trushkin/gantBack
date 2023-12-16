package com.example.ganttback.resource;

import com.example.ganttback.gantt.task.GanttTask;
import com.example.ganttback.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "resources")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "salary")
    private Long salary;
    @Column(name = "capacity")
    private Long capacity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<GanttTask> tasks;

    public Resource(Long id, String name, Long salary, Long capacity, User user, List<GanttTask> tasks) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.capacity = capacity;
        this.user = user;
        this.tasks = tasks;
    }

    public Resource(Long id, String name, Long salary, Long capacity, User user) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.capacity = capacity;
        this.user = user;
    }

    public List<GanttTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<GanttTask> tasks) {
        this.tasks = tasks;
    }

    public Resource() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
