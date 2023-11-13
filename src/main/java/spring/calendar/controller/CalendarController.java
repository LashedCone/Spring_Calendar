package spring.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.calendar.dto.CalendarDTO;
import spring.calendar.entity.Calendar;
import spring.calendar.service.CalendarService;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
    @Autowired
    CalendarService calendarService;
    
    @PostMapping("{userId}")
    public ResponseEntity<?> createCalendar(@PathVariable long userId, @RequestBody CalendarDTO calendarDTO) {
        try {
            Calendar calendar = calendarService.createCalendar(userId, calendarDTO);
            return ResponseEntity.ok(calendar);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getAllCalendar() {
        return ResponseEntity.ok(calendarService.getAllCalendar());
    }
    
    @GetMapping("{id}")
    public ResponseEntity<?> getCalendarById(@PathVariable long id) {
        return ResponseEntity.ok(calendarService.searchCalendarById(id));
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCalendar(@PathVariable long id) {
        if(calendarService.searchCalendarById(id) == null) {
            return ResponseEntity.status(404).body(String.format("Calendar not found: id %d", id));
        }
        calendarService.deleteCalendar(id);
        return ResponseEntity.ok(String.format("Calendar with id %d has been deleted", id));
    }
    
//    @GetMapping
//    public ResponseEntity<?> searchCalendarByName(@RequestParam String name) {
//        return ResponseEntity.ok(calendarService.searchCalendarByName(name));
//    }
    
//    @GetMapping
//    public ResponseEntity<?> searchCalendarByDescription(@RequestParam String calendarDescription) {
//        return ResponseEntity.ok(calendarService.searchCalendarByDescription(calendarDescription));
//    }
    
    @PatchMapping("{userId}/{id}")
    public ResponseEntity<?> updateCalendar(@PathVariable long userId, @PathVariable long id, @RequestBody CalendarDTO updatedCalendar) {
        Calendar modifiedCalendar = calendarService.updateCalendar(userId, id, updatedCalendar);
        if(modifiedCalendar == null) {
            return ResponseEntity.status(404).body(String.format("Calendar not found: id %d", id));
        }
        return ResponseEntity.ok(modifiedCalendar);
    }
}
