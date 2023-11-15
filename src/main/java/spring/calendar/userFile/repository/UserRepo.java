package spring.calendar.userFile.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import spring.calendar.userFile.model.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
