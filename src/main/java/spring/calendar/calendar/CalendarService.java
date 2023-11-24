package spring.calendar.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.user.User;
import spring.calendar.user.UserDTO;
import spring.calendar.user.UserRepo;

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

	public Calendar updateCalendar(Long userId, Long calendarId, Calendar updatedCalendar) {
		Optional<User> existingUser = userRepo.findById(userId);
		Calendar existingCalendar = calendarRepo.findById(calendarId).get();
		if(existingUser.isPresent()) {
			if(existingCalendar.getUser().getId().equals(userId)) {
				if(updatedCalendar.getCalendarName() != null) {
					existingCalendar.setCalendarName(updatedCalendar.getCalendarName());
				}
				if(updatedCalendar.getCalendarDescription() != null) {
					existingCalendar.setCalendarDescription(updatedCalendar.getCalendarDescription());
				}
				return calendarRepo.save(existingCalendar);
			}
		}
		return null;
	}
}
