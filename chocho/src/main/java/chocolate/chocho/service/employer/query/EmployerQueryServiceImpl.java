package chocolate.chocho.service.employer.query;

import chocolate.chocho.dto.EmployerQueryDto;
import chocolate.chocho.entity.Employer;
import chocolate.chocho.repository.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployerQueryServiceImpl implements EmployerQueryService {

    private final EmployerRepository employerRepository;

    @Override
    public EmployerQueryDto findById(UUID employerId) {
        //todo 오류 처리 추가하기
        Employer employer = employerRepository.findById(employerId).orElseThrow();
        return convertToDto(employer);
    }

    EmployerQueryDto convertToDto(Employer employer) {
        return new EmployerQueryDto(employer.getName(), employer.getAddress());
    }
}
