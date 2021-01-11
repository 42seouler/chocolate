package chocolate.chocho.service.employer.query;

import chocolate.chocho.dto.EmployerQueryDto;
import chocolate.chocho.entity.Employer;
import chocolate.chocho.repository.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<EmployerQueryDto> findAll(int page, int size) {
        //todo 오류 처리 추가하기
        Page<Employer> employers = employerRepository.findAll(PageRequest.of(page, size));
        return employers.map(this::convertToDto);
    }

    EmployerQueryDto convertToDto(Employer employer) {
        return new EmployerQueryDto(employer.getName(), employer.getAddress());
    }
}
