package chocolate.chocho.service.user.query;

import chocolate.chocho.dto.UserQueryDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface UserQueryService {

    UserQueryDto findById(UUID userId);

    Page<UserQueryDto> findAll(int page, int size);
}
