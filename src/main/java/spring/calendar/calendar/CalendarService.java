package spring.calendar.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.userFile.User;
import spring.calendar.userFile.UserRepo;

import java.util.Optional;

@Service
public class CalendarService {
	@Autowired
	CalendarRepo calendarRepo;
	@Autowired
	UserRepo userRepo;

	public Calendar createUserCalendar(Long userId, Calendar newCalendar) {
		User user = userRepo.findById(userId).orElseThrow(IllegalArgumentException::new);
		newCalendar.setUser(user);
		user.getCalendars().add(newCalendar);
		return calendarRepo.save(newCalendar);
	}

	public Optional<Calendar> findCalendarById(Long id) {
		return calendarRepo.findById(id);
	}

	public Iterable<Calendar> findAllCalendars() {
		return calendarRepo.findAll();
	}

	public void deleteCalendar(Long id) {
		calendarRepo.deleteById(id);
	}

	public Calendar updateCalendar(Long id, Calendar updatedCalendar) {
		Optional<Calendar> existingCalendar = calendarRepo.findById(id);
		if(existingCalendar.isPresent()) {
			Calendar calendar = existingCalendar.get();
			if(updatedCalendar.getCalendarName() != null) {
				calendar.setCalendarName(updatedCalendar.getCalendarName());
			}
			if(updatedCalendar.getCalendarDescription() != null) {
				calendar.setCalendarDescription(updatedCalendar.getCalendarDescription());
			}
			return calendarRepo.save(calendar);
		}
		return null;
	}
}
