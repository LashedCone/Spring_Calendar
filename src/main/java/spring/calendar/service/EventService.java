package spring.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.dto.EventDTO;
import spring.calendar.entity.Event;
import spring.calendar.repository.EventRepository;

import java.util.Optional;
@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    
    public Event createEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setEventName(eventDTO.getEventName());
        event.setDescription(eventDTO.getDescription());
        event.setEventStart(eventDTO.getEventStart());
        event.setEventDuration(eventDTO.getEventDuration());
        event.setEventEnd(eventDTO.getEventEnd());
        return eventRepository.save(event);
    }
    
    public void deleteEvent(long id) {
        eventRepository.deleteById(id);
    }
    
    public Optional<Event> getEvent(long id) {
        return eventRepository.findById(id);
    }
    
    public Iterable<Event> getAllEvent() {
        return eventRepository.findAll();
    }
    
    public Event searchEventByName(String eventName) {
        return eventRepository.findByEventName(eventName);
    }
    
    public Event updateEvent(long id, EventDTO updatedEvent) {
        Event existingEvent = eventRepository.findById(id).orElse(null);
        if(existingEvent != null) {
            if(updatedEvent.getEventName() != null) {
                existingEvent.setEventName(updatedEvent.getEventName());
            }
            if(updatedEvent.getDescription() != null) {
                existingEvent.setDescription(updatedEvent.getDescription());
            }
            if(updatedEvent.getEventStart() != null) {
                existingEvent.setEventStart(updatedEvent.getEventStart());
            }
            if(updatedEvent.getEventDuration() != null) {
                existingEvent.setEventDuration(updatedEvent.getEventDuration());
            }
            if(updatedEvent.getEventEnd() != null) {
                existingEvent.setEventEnd(updatedEvent.getEventEnd());
            }
            return eventRepository.save(existingEvent);
        }
        return null;
    }
}