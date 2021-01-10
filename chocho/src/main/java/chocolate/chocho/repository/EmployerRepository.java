package chocolate.chocho.repository;

import chocolate.chocho.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployerRepository extends JpaRepository<Employer, UUID> {
}
