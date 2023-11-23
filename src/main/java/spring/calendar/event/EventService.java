package spring.calendar.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.calendar.Calendar;
import spring.calendar.calendar.CalendarRepo;
import spring.calendar.user.User;

import java.util.Optional;

@Service
public class EventService {
	@Autowired
	EventRepo eventRepo;
	@Autowired
	CalendarRepo calendarRepo;

	public Event createEvent(Long calendarId, Event event) {
		Calendar calendar = calendarRepo.findById(calendarId).orElseThrow(IllegalArgumentException::new);
		User user = calendar.getUser();
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
		eventToSave.getUsersList().add(user);
		eventToSave.setEventCalendar(calendar);
		calendar.getEvents().add(eventToSave);
		return eventRepo.save(eventToSave);
	}

	public void deleteEvent(Long id) {
		eventRepo.deleteById(id);
	}

	public Iterable<Event> getAllEvents() {
		return eventRepo.findAll();
	}

	public Optional<Event> getEventById(Long id) {
		return eventRepo.findById(id);
	}

	public Event updateEvent(Long id, Event updatedEvent) {
		Optional<Event> existingEvent = eventRepo.findById(id);
		if(existingEvent.isPresent()) {
			Event event = existingEvent.get();
			if(updatedEvent.getEventName() != null) {
				event.setEventName(updatedEvent.getEventName());
			}
			if(updatedEvent.getEventDescription() != null) {
				event.setEventDescription(updatedEvent.getEventDescription());
			}
			if(updatedEvent.getEventStart() != null) {
				event.setEventStart(updatedEvent.getEventStart());
			}
			if(updatedEvent.getEventEnd() != null) {
				event.setEventEnd(updatedEvent.getEventEnd());
			}
			return eventRepo.save(event);
		}
		return null;
	}

	public Iterable<Event> getEventByName(String eventName) {
		return eventRepo.findByEventName(eventName);
	}
}
