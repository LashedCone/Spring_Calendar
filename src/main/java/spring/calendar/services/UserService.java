package spring.calendar.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.models.entities.User;
import spring.calendar.repositories.UserRepo;

import java.util.Optional;

@Service
public class UserService {
	@Autowired
	UserRepo userRepo;

	@Transactional
	public User createUser(User user) {
		return userRepo.save(user);
	}

	@Transactional
	public User updateUser(Long id, User updatedUser) {
			User user = userRepo.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
				user.setFirstName(updatedUser.getFirstName());
				user.setLastName(updatedUser.getLastName());
				user.setEmail(updatedUser.getEmail());
				user.setPassword(updatedUser.getPassword());
				return userRepo.save(user);
	}

	public Optional<User> findUserById(Long id) {
		return userRepo.findById(id);
	}

	public Iterable<User> findAllUsers() {
		return userRepo.findAll();
	}

	public void deleteUserById(Long id) {
		userRepo.deleteById(id);
	}

	@Transactional
	public User getUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
}
