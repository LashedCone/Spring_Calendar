package spring.calendar.calendar.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import spring.calendar.calendar.model.entity.Calendar;

@Repository
public interface CalendarRepo extends JpaRepository<Calendar, Long> {
}
