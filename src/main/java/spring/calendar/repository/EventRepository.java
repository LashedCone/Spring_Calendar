package spring.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.calendar.entity.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByEventName(String eventName);
}
