package spring.calendar.calendar.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.calendar.model.entity.Calendar;
import spring.calendar.calendar.repository.CalendarRepo;
import spring.calendar.repository.*;
import spring.calendar.userFile.model.entity.User;
import spring.calendar.userFile.repository.UserRepo;

import java.util.Optional;

@Service
public class CalendarService {
	@Autowired
	CalendarRepo calendarRepo;
	@Autowired
	UserRepo userRepo;

	@Transactional
	public Calendar createNewCalendar(Calendar calendar) {
		return calendarRepo.save(calendar);
	}

	@Transactional
	public Calendar createUserCalendar(Long userId, String calendarName) {
		User user = userRepo.findById(userId).orElseThrow(IllegalArgumentException::new);
		user.setId(userId);
		Calendar calendar = new Calendar();
		calendar.setUser(user);
		calendar.setCalendarName(calendarName);
		return calendarRepo.save(calendar);
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

	@Transactional
	public Calendar updateCalendar(Long id, Calendar updatedCalendar) {
		Calendar calendar = calendarRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Calendar not found with id: " + id));
		calendar.setCalendarName(updatedCalendar.getCalendarName());
		calendar.setCalendarDescription(updatedCalendar.getCalendarDescription());
		return calendarRepo.save(calendar);
	}
}
