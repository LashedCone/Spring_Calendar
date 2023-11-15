package spring.calendar.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.models.entities.Calendar;
import spring.calendar.models.entities.Event;
import spring.calendar.repositories.CalendarRepo;
import spring.calendar.repositories.EventRepo;

import java.util.Optional;

@Service
public class EventService {
	@Autowired
	private EventRepo eventRepo;
	@Autowired
	CalendarRepo calendarRepo;

	@Transactional
	public Event createEvent(Event event) {
		return eventRepo.save(event);
	}

	@Transactional
	public Event createEventForCalendar(Long calendarId, Event event) {
		Calendar calendar = calendarRepo.findById(calendarId).orElseThrow(IllegalArgumentException::new);
		Event eventToSave = new Event();
		if(event.getEventName() != null) {
			eventToSave.setEventName(event.getEventName());
		}
		if(event.getEventDescription() != null) {
			eventToSave.setEventDescription(event.getEventDescription());
		}
		if(event.getEventStart() != null) {
			eventToSave.setEventStart(event.getEventStart());
		}
		if(event.getEventEnd() != null) {
			eventToSave.setEventEnd(event.getEventEnd());
		}
		return eventRepo.save(eventToSave);
	}

	public void deleteEvent(long id) {
		eventRepo.deleteById(id);
	}

	public Iterable<Event> getAllEvents() {
		return eventRepo.findAll();
	}

	public Optional<Event> getEvent(long id) {
		return eventRepo.findById(id);
	}

	@Transactional
	public Event updateEvent(long id, Event updatedEvent) {
		Event existingEvent = eventRepo.findById(id).orElseThrow(IllegalArgumentException::new);
			if(updatedEvent.getEventName() != null) {
				existingEvent.setEventName(updatedEvent.getEventName());
			}
			if(updatedEvent.getEventDescription() != null) {
				existingEvent.setEventDescription(updatedEvent.getEventDescription());
			}
			if(updatedEvent.getEventStart() != null) {
				existingEvent.setEventStart(updatedEvent.getEventStart());
			}
			if(updatedEvent.getEventEnd() != null) {
				existingEvent.setEventEnd(updatedEvent.getEventEnd());
			}
			return eventRepo.save(existingEvent);
	}

	public Iterable<Event> getEventByName(String eventName) {
		return eventRepo.findByEventName(eventName);
	}

	public Iterable<Event> getEventsByCalendarId(long calendarId) {
		return eventRepo.findEventByCalendarId(calendarId);
	}
}
