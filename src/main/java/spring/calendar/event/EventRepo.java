package spring.calendar.event;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {
	Iterable<Event> findByEventName(String eventName);
}