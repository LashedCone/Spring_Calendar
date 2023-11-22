package spring.calendar.userFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.createUser(user));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		if (userService.findUserById(id).isPresent()) {
			return ResponseEntity.ok(userService.findUserById(id));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/info/{id}")
	public ResponseEntity<?> getUserBasicInfo(@PathVariable Long id) {
		if (userService.findUserById(id).isPresent()) {
			return ResponseEntity.ok(userService.getUserBasicInfo(id));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userService.findAllUsers());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
		if (userService.findUserById(id).isPresent()) {
			userService.deleteUserById(id);
			return ResponseEntity.ok("Deleted user with id: " + id);
		} else {
			return ResponseEntity.status(404).body("User not found with id: " + id);
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
		if (userService.findUserById(id).isPresent()) {
			return ResponseEntity.ok(userService.updateUser(id, updatedUser));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/search/email{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
		return ResponseEntity.ok(userService.getUserByEmail(email));
	}
}
