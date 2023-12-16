package com.example.ganttback.gantt.link;

public class GanttLinkDtoBuilder {
    private final GanttLinkDto ganttLinkDto;

    public GanttLinkDtoBuilder() {
        this.ganttLinkDto = new GanttLinkDto();
    }
    public GanttLinkDtoBuilder setId(Long id){
        this.ganttLinkDto.setId(id);
        return this;
    }
    public GanttLinkDtoBuilder setSource(Long source){
        this.ganttLinkDto.setSource(source);
        return this;
    }
    public GanttLinkDtoBuilder setTarget(Long target){
        this.ganttLinkDto.setTarget(target);
        return this;
    }
    public GanttLinkDtoBuilder setType(Long type){
        this.ganttLinkDto.setType(type);
        return this;
    }
    public GanttLinkDtoBuilder setUserId(Long userId){
        this.ganttLinkDto.setUserId(userId);
        return this;
    }
    public GanttLinkDto build(){
        return ganttLinkDto;
    }
}
