package spring.calendar.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {
	@Autowired
	EventService eventService;

	@GetMapping
	public ResponseEntity<Iterable<Event>> viewAllEvents() {
		return ResponseEntity.ok(eventService.getAllEvents());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Event>> viewEventById(@PathVariable Long id) {
		return ResponseEntity.ok(eventService.getEventById(id));
	}

	@GetMapping("/name/{eventName}")
	public ResponseEntity<Iterable<Event>> viewEventByName(@PathVariable String eventName) {
		return ResponseEntity.ok(eventService.getEventByName(eventName));
	}

	@PostMapping("/{id}")
	public ResponseEntity<?> createEvent(@PathVariable Long id, @RequestBody Event event) {
		return ResponseEntity.ok(eventService.createEvent(id, event));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
		if(eventService.getEventById(id).isPresent()) {
			eventService.deleteEvent(id);
			return ResponseEntity.ok("Event deleted");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> modifyEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
		if(eventService.getEventById(id).isPresent()) {
			return ResponseEntity.ok(eventService.updateEvent(id, updatedEvent));
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
