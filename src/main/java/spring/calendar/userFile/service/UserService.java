package spring.calendar.userFile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.userFile.model.dto.UserDTO;
import spring.calendar.userFile.model.entity.User;
import spring.calendar.userFile.repository.UserRepo;

import java.util.Optional;

@Service
public class UserService {
	@Autowired
	UserRepo userRepo;

	public User createUser(User user) {
		return userRepo.save(user);
	}

	public User updateUser(Long id, User updatedUser) {
	Optional<User> existingUser = userRepo.findById(id);
		if(existingUser.isPresent()) {
		User user = existingUser.get();
		if(updatedUser.getFirstName() != null) {
			user.setFirstName(updatedUser.getFirstName());
		}
		if(updatedUser.getLastName() != null) {
			user.setLastName(updatedUser.getLastName());
		}
		if(updatedUser.getEmail() != null) {
			user.setEmail(updatedUser.getEmail());
		}
		if(updatedUser.getPassword() != null) {
			user.setPassword(updatedUser.getPassword());
		}
		return userRepo.save(user);
	}
		return null;
	}

	public Optional<User> findUserById(Long id) {
		return userRepo.findById(id);
	}

	public UserDTO getUserBasicInfo(Long id) {
		Optional<User> userOptional = userRepo.findById(id);

		if(userOptional.isPresent()) {
			User user = userOptional.get();
			return new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail());
		}
			return null;
	}

	public Iterable<User> findAllUsers() {
		return userRepo.findAll();
	}

	public void deleteUserById(Long id) {
		userRepo.deleteById(id);
	}

	public User getUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
}
