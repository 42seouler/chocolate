package chocolate.chocho.repository.store;

import chocolate.chocho.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
