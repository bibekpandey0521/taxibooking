package taxi.booking.project.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import taxi.booking.project.model.Admin;

public interface AdminDao extends JpaRepository<Admin, Integer> {
	
	Optional<Admin> findByUsername(String username);  // ✅ fixed
	
}
