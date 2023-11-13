package spring.calendar.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    private String eventName;
    private String description;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime eventStart;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime eventDuration;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime eventEnd;
    @ManyToMany
    private List<User> usersList;
    @ManyToOne
    @JsonIgnore
    private Calendar calendar;
    
    public Event() {}
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
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
    
    public List<User> getUsersList() {
        return usersList;
    }
    
    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }
    
    public Calendar getCalendar() {
        return calendar;
    }
    
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}