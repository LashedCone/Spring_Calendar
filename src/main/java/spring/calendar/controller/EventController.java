package spring.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.calendar.dto.EventDTO;
import spring.calendar.entity.Event;
import spring.calendar.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventService eventService;
    
    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody EventDTO eventDTO) {
        try {
            Event event = eventService.createEvent(eventDTO);
            return ResponseEntity.ok(event);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getAllEvent() {
        return ResponseEntity.ok(eventService.getAllEvent());
    }
    
    @GetMapping("{id}")
    public ResponseEntity<?> getEventById(@PathVariable long id) {
        return ResponseEntity.ok(eventService.getEvent(id));
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable long id) {
        if(eventService.getEvent(id).isEmpty()) {
            return ResponseEntity.status(404).body(String.format("Event not found: id %d", id));
        }
        eventService.deleteEvent(id);
        return ResponseEntity.ok(String.format("Event with id %d has been deleted", id));
    }
    
    @GetMapping
    public ResponseEntity<?> searchEventByName(@RequestParam String eventName) {
        return ResponseEntity.ok(eventService.searchEventByName(eventName));
    }
    
    @PatchMapping("{id}")
    public ResponseEntity<?> updateEvent(@PathVariable long id, @RequestBody EventDTO updatedEvent) {
        Event modifiedEvent = eventService.updateEvent(id, updatedEvent);
        if(modifiedEvent == null) {
            return ResponseEntity.status(404).body(String.format("Event not found: id %d", id));
        }
        return ResponseEntity.ok(modifiedEvent);
    }
}
