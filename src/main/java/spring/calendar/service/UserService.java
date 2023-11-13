package spring.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.calendar.dto.UserDTO;
import spring.calendar.entity.User;
import spring.calendar.repository.UserRepository;

import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }
    
    public Optional<User> searchUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
    
    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }
    
    public User updateUser(long id, UserDTO updatedUser) {
        User existingUser = userRepository.findById(id).orElse(null);
        if(existingUser != null) {
            if(updatedUser.getFirstName() != null) {
                existingUser.setFirstName(updatedUser.getFirstName());
            }
            if(updatedUser.getLastName() != null) {
                existingUser.setLastName(updatedUser.getLastName());
            }
            if(updatedUser.getAge() != 0) {
                existingUser.setAge(updatedUser.getAge());
            }
            if(updatedUser.getEmail() != null) {
                existingUser.setEmail(updatedUser.getEmail());
            }
            if(updatedUser.getPassword() != null) {
                existingUser.setPassword(updatedUser.getPassword());
            }
            return userRepository.save(existingUser);
        }
        return null;
    }
}
