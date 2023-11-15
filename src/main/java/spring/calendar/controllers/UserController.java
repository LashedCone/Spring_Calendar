package spring.calendar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.calendar.models.entities.User;
import spring.calendar.services.UserService;

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

	@GetMapping("/all")
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userService.findAllUsers());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
		if (userService.findUserById(id).isPresent()) {
			userService.deleteUserById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
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

	@GetMapping("/{email}")
	public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
		return ResponseEntity.ok(userService.getUserByEmail(email));
	}
}
