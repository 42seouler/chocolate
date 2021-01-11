package chocolate.chocho.service.employer.query;

import chocolate.chocho.dto.EmployerQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EmployerQueryService {

    EmployerQueryDto            findById(UUID employerId);
    Page<EmployerQueryDto>      findAll(int page, int size);
}
