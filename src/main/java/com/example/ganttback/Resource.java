package com.example.ganttback;

public class Resource {
    private Long id;
    private String name;
    private Long capacity;
    private Long occupancy;
    private Long salary;
    private Long totalCost;

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
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

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(Long occupancy) {
        this.occupancy = occupancy;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Resource(Long id, String name, Long capacity, Long occupancy, Long salary) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.occupancy = occupancy;
        this.salary = salary;
    }

    public Resource() {}
}
