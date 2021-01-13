package chocolate.chocho.repository;

import chocolate.chocho.entity.JobOpening;
import chocolate.chocho.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobOpeningRepository extends JpaRepository<JobOpening, Long> {
    Optional<JobOpening> findByStore(Store store);
}
