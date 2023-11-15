package spring.calendar.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import spring.calendar.models.entities.Calendar;

@Repository
public interface CalendarRepo extends JpaRepository<Calendar, Long> {
}
