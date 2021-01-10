package chocolate.chocho.repository;

import chocolate.chocho.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, UUID> {
}
