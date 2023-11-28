package spring.calendar.event;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {
	Iterable<Event> findByEventName(String eventName);

	Iterable<Event> findEventByEventStart(LocalDateTime eventStart);

	Iterable<Event> findEventByEventStartBetween(LocalDateTime eventStart, LocalDateTime eventEnd);
}