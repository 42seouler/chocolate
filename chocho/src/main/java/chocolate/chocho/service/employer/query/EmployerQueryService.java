package chocolate.chocho.service.employer.query;

import chocolate.chocho.dto.EmployerQueryDto;

import java.util.UUID;

public interface EmployerQueryService {

    EmployerQueryDto findById(UUID employerId);
}
