package spring.calendar.dto;

public class CalendarDTO {
    private String calendarName;
    private String description;
    
    public CalendarDTO() {}
    
    public String getCalendarName() {
        return calendarName;
    }
    
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
