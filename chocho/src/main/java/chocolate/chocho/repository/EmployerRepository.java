package chocolate.chocho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployerRepository extends JpaRepository<Employer, UUID> {
}
