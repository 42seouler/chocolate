package chocolate.chocho.repository;

import chocolate.chocho.entity.Store;
import chocolate.chocho.entity.StoreManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreManagementRepository extends JpaRepository<StoreManagement, Long> {

    Optional<StoreManagement> findByStore(Store store);
}
