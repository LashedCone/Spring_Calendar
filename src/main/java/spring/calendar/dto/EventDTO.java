package spring.calendar.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class EventDTO {
    private String eventName;
    private String description;
    private LocalDateTime eventStart;
    private LocalDateTime eventDuration;
    private LocalDateTime eventEnd;
    
    public EventDTO() {
    }
    
    public String getEventName() {
        return eventName;
    }
    
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDateTime getEventStart() {
        return eventStart;
    }
    
    public void setEventStart(LocalDateTime eventStart) {
        this.eventStart = eventStart;
    }
    
    public LocalDateTime getEventDuration() {
        return eventDuration;
    }
    
    public void setEventDuration(LocalDateTime eventDuration) {
        this.eventDuration = eventDuration;
    }
    
    public LocalDateTime getEventEnd() {
        return eventEnd;
    }
    
    public void setEventEnd(LocalDateTime eventEnd) {
        this.eventEnd = eventEnd;
    }
}
