package chocolate.chocho.repository.storeuser;

import chocolate.chocho.entity.StoreUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreUserRepository extends JpaRepository<StoreUser, UUID> {
}
