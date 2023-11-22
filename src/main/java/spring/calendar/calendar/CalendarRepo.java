package spring.calendar.calendar;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepo extends JpaRepository<Calendar, Long> {
}
