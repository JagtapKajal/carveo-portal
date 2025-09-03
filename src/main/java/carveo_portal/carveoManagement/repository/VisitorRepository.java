package carveo_portal.carveoManagement.repository;

import carveo_portal.carveoManagement.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
}
