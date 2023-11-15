package spring.calendar.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import spring.calendar.models.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
