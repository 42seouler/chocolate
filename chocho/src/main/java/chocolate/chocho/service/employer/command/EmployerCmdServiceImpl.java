package chocolate.chocho.service.employer.command;

import chocolate.chocho.dto.EmployerCmdDto;
import chocolate.chocho.entity.Employer;
import chocolate.chocho.repository.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployerCmdServiceImpl implements EmployerCmdService {

    private final EmployerRepository employerRepository;

    @Override
    public UUID create(EmployerCmdDto dto) {
        //todo 오류 검사 추가하기 ex) name length, NULL 여부 등
        Employer newEmployer = new Employer(dto.getName(), dto.getAddress());
        Employer saveEmployer = employerRepository.save(newEmployer);
        return saveEmployer.getId();
    }

    @Override
    public EmployerCmdDto update(UUID employerId, EmployerCmdDto dto) {
        //todo 오류 검사 추가하기
        Employer findEmployer = employerRepository.findById(employerId).orElseThrow();
        findEmployer.update(dto.getName(), dto.getAddress());
        return entityToDto(findEmployer);
    }

    @Override
    public void delete(UUID employerId) {
        //todo 오류 검사 추가하기
        Employer findEmployer = employerRepository.findById(employerId).orElseThrow();
        employerRepository.delete(findEmployer);
    }

    EmployerCmdDto entityToDto(Employer employer) {
        return new EmployerCmdDto(employer.getName(), employer.getAddress());
    }
}
