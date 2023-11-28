package spring.calendar.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.calendar.Calendar;
import spring.calendar.calendar.CalendarRepo;
import spring.calendar.user.User;
import spring.calendar.user.UserRepo;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EventService {
	@Autowired
	EventRepo eventRepo;
	@Autowired
	CalendarRepo calendarRepo;
	@Autowired
	UserRepo userRepo;

	public void createEvent(Long calendarId, Event event) {
		Calendar calendar = calendarRepo.findById(calendarId).orElseThrow(IllegalArgumentException::new);
		User user = calendar.getUser();

		LocalDateTime start = event.getEventStart();
		LocalDateTime end = event.getEventStart().plusHours(1);
		LocalDateTime eventEnd = event.getEventEnd();

		while(start.isBefore(eventEnd) || start.isEqual(eventEnd)) {
			Event eventToSave = new Event();
			eventToSave.setEventName(event.getEventName());
			eventToSave.setEventDescription(event.getEventDescription());

			switch(event.getFrequency()) {
				case NO_FREQUENCY:
					eventToSave.setEventStart(start);
					eventToSave.setEventEnd(end);
					start = eventEnd.plusNanos(1);
					break;
				case DAILY:
					eventToSave.setEventStart(start);
					eventToSave.setEventEnd(end);
					start = start.plusDays(1);
					end = end.plusDays(1);
					break;
				case WEEKLY:
					eventToSave.setEventStart(start);
					eventToSave.setEventEnd(end);
					start = start.plusWeeks(1);
					end = end.plusWeeks(1);
					break;
				case MONTHLY:
					eventToSave.setEventStart(start);
					eventToSave.setEventEnd(end);
					start = start.plusMonths(1);
					end = end.plusMonths(1);
					break;
				case YEARLY:
					eventToSave.setEventStart(start);
					eventToSave.setEventEnd(end);
					start = start.plusYears(1);
					end = end.plusYears(1);
					break;
			}
			eventToSave.getUsersList().add(user);
			eventToSave.setEventCalendar(calendar);
			calendar.getEvents().add(eventToSave);
			eventRepo.save(eventToSave);
		}
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

	public Iterable<Event> findEventByEventStart(LocalDateTime eventStart) {
		return eventRepo.findEventByEventStart(eventStart);
	}

	public Iterable<Event> findEventByEventStartBetween(LocalDateTime eventStart, LocalDateTime eventEnd) {
		return eventRepo.findEventByEventStartBetween(eventStart, eventEnd);
	}

	public void invitation(Long eventId, Long userId) {
		Event event = eventRepo.findById(eventId).orElseThrow(IllegalArgumentException::new);
		User user = userRepo.findById(userId).orElseThrow(IllegalArgumentException::new);

		event.getUsersList().add(user);
		user.getUserEvents().add(event);
		eventRepo.save(event);
		userRepo.save(user);
	}
}