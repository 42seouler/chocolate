package chocolate.chocho.repository;

import chocolate.chocho.entity.PostManagement;
import chocolate.chocho.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostManagementRepository extends JpaRepository<PostManagement, Long> {
    Optional<PostManagement> findByStore(Store store);
}
