package chocolate.chocho.repository;

import chocolate.chocho.entity.Store;
import chocolate.chocho.entity.StoreMgmt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreMgmtRepository extends JpaRepository<StoreMgmt, Long> {

    Optional<StoreMgmt> findByStore(Store store);
}
