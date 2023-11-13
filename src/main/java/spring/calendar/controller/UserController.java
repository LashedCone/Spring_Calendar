package spring.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.calendar.dto.UserDTO;
import spring.calendar.entity.User;
import spring.calendar.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try {
            User user = userService.createUser(userDTO);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }
    
    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable long id) {
        if(userService.getUserById(id).isEmpty()) {
            return ResponseEntity.status(404).body(String.format("User not found: id %d", id));
        }
        userService.deleteUserById(id);
        return ResponseEntity.ok(String.format("User with id %d has been deleted", id));
    }
    
    @GetMapping
    public ResponseEntity<?> searchUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.searchUserByEmail(email));
    }
    
    @PatchMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody UserDTO updatedUser) {
        User modifiedUser = userService.updateUser(id, updatedUser);
        if(modifiedUser == null) {
            return ResponseEntity.status(404).body(String.format("User not found: id %d", id));
        }
        return ResponseEntity.ok(modifiedUser);
    }
}
