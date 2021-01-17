package chocolate.chocho.repository;

import chocolate.chocho.entity.Store;
import chocolate.chocho.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByUser(User user);
}
