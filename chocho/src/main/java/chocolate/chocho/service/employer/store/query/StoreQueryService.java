package chocolate.chocho.service.employer.store.query;

import chocolate.chocho.dto.StoreQueryDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface StoreQueryService {

    StoreQueryDto       findById(UUID storeId);

    Page<StoreQueryDto> findAll(int page, int size);
}
