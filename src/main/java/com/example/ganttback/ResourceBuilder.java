package com.example.ganttback;

public class ResourceBuilder {
    private Resource resource;

    public ResourceBuilder() {
        this.resource = new Resource();
    }

    public ResourceBuilder setId(Long id) {
        this.resource.setId(id);
        return this;
    }

    public ResourceBuilder setName(String name) {
        this.resource.setName(name);
        return this;
    }

    public ResourceBuilder setCapacity(Long capacity) {
        this.resource.setCapacity(capacity);
        return this;
    }

    public ResourceBuilder setOccupancy(Long occupancy) {
        this.resource.setOccupancy(occupancy);
        return this;
    }

    public ResourceBuilder setSalary(Long salary) {
        this.resource.setSalary(salary);
        return this;
    }
    public ResourceBuilder setTotalCost(Long totalCost){
        this.resource.setTotalCost(totalCost);
        return this;
    }

    public Resource build() {
        return resource;
    }

}
