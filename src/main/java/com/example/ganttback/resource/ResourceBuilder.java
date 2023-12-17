package com.example.ganttback.resource;

import com.example.ganttback.user.User;

public class ResourceBuilder {
    private Resource resource;

    public ResourceBuilder() {
        this.resource = new Resource();
    }

    public ResourceBuilder setId(Long id){
        this.resource.setId(id);
        return this;
    }
    public ResourceBuilder setName(String name){
        this.resource.setName(name);
        return this;
    }
    public ResourceBuilder setSalary(Long salary){
        this.resource.setSalary(salary);
        return this;
    }
    public ResourceBuilder setCapacity(Long capacity){
        this.resource.setCapacity(capacity);
        return this;
    }
    public ResourceBuilder setUser(User user){
        this.resource.setUser(user);
        return this;
    }

    public Resource build(){
        return resource;
    }
}
