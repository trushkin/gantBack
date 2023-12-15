package com.example.ganttback.resource;

public class ResourceDtoBuilder {
    private ResourceDto resource;

    public ResourceDtoBuilder() {
        this.resource = new ResourceDto();
    }

    public ResourceDtoBuilder setId(Long id) {
        this.resource.setId(id);
        return this;
    }

    public ResourceDtoBuilder setName(String name) {
        this.resource.setName(name);
        return this;
    }

    public ResourceDtoBuilder setCapacity(Long capacity) {
        this.resource.setCapacity(capacity);
        return this;
    }

    public ResourceDtoBuilder setOccupancy(Long occupancy) {
        this.resource.setOccupancy(occupancy);
        return this;
    }

    public ResourceDtoBuilder setSalary(Long salary) {
        this.resource.setSalary(salary);
        return this;
    }
    public ResourceDtoBuilder setTotalCost(Long totalCost){
        this.resource.setTotalCost(totalCost);
        return this;
    }
    public ResourceDtoBuilder setUserId(Long userId){
        this.resource.setUserId(userId);
        return this;
    }

    public ResourceDto build() {
        return resource;
    }

}
