package taxi.booking.project.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import taxi.booking.project.model.Admin;

public interface AdminDao extends JpaRepository<Admin, Integer> {
	
	Optional<Admin> findByUsername(String username);  
	
	@Modifying
	@Transactional
	@Query(value = "update admin set username=:newusername,password=:newpassword",
	nativeQuery = true)
	public int updateCredentials(
			@Param("newusername") String newusername,
			@Param("newpassword") String newpassword,
			@Param("newusername") String oldusername
	);
}
