package spring.calendar.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
	@Autowired
	CalendarService calendarService;

	@PostMapping("/{userId}")
	public ResponseEntity<Calendar> createCalendar(@PathVariable Long userId, @RequestBody Calendar newCalendar) {
		return ResponseEntity.ok(calendarService.createUserCalendar(userId, newCalendar));
	}

	@GetMapping
	public ResponseEntity<Iterable<Calendar>> viewAllCalendar() {
		return ResponseEntity.ok(calendarService.findAllCalendars());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Calendar>> searchCalendarById(@PathVariable Long id) {
		return ResponseEntity.ok(calendarService.findCalendarById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCalendarById(@PathVariable Long id) {
		calendarService.deleteCalendar(id);
		return ResponseEntity.ok("Calendar deleted");
	}

	@PatchMapping("userID/{userId}/calendarID/{calendarId}")
	public ResponseEntity<?> updateCalendar(@PathVariable Long userId, @PathVariable Long calendarId, @RequestBody Calendar updatedCalendar) {
		if(calendarService.findCalendarById(calendarId).isPresent()) {
			return ResponseEntity.ok(calendarService.updateCalendar(userId, calendarId, updatedCalendar));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}