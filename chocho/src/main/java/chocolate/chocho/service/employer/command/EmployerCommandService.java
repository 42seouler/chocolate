package chocolate.chocho.service.employer.command;

import chocolate.chocho.dto.EmployerCmdDto;

import java.util.UUID;

public interface EmployerCommandService {

    UUID            create(EmployerCmdDto dto);

    EmployerCmdDto update(UUID employerId, EmployerCmdDto dto);
}
