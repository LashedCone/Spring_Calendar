package spring.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.calendar.entity.Calendar;
@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    Calendar findByCalendarName(String calendarName);
    
    Iterable<Calendar> findByDescription(String description);
}
