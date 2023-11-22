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

	@PostMapping
	public ResponseEntity<Calendar> createCalendar(@PathVariable Long userId, Calendar newCalendar) {
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

	@PatchMapping("/{id}")
	public ResponseEntity<?> updateCalendar(@PathVariable Long id, @RequestBody Calendar calendar) {
		if(calendarService.findCalendarById(id).isPresent()) {
			return ResponseEntity.ok(calendarService.updateCalendar(id, calendar));
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}