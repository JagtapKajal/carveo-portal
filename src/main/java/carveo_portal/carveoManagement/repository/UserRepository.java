package carveo_portal.carveoManagement.repository;

import carveo_portal.carveoManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
