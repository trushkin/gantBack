package com.example.ganttback.user;

import com.example.ganttback.gantt.link.GanttLink;
import com.example.ganttback.gantt.task.GanttTask;
import com.example.ganttback.resource.Resource;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Resource> resources;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<GanttTask> tasks;

    public User(Long id, String email, String password, List<Resource> resources, List<GanttTask> tasks, List<GanttLink> links) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.resources = resources;
        this.tasks = tasks;
        this.links = links;
    }

    public List<GanttTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<GanttTask> tasks) {
        this.tasks = tasks;
    }

    public List<GanttLink> getLinks() {
        return links;
    }

    public void setLinks(List<GanttLink> links) {
        this.links = links;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<GanttLink> links;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
