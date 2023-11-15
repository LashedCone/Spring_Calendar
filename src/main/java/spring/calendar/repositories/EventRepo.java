package spring.calendar.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import spring.calendar.models.entities.Calendar;
import spring.calendar.models.entities.Event;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {
	Iterable<Event> findByEventName(String eventName);


	Iterable<Event> findEventByCalendarId(long calendarId);
}
