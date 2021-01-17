package chocolate.chocho.service.user.command;

import chocolate.chocho.dto.UserCmdDto;
import chocolate.chocho.entity.user.User;
import chocolate.chocho.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCmdServiceImpl implements UserCmdService {

    private final UserRepository userRepository;

    @Override
    public UUID create(UserCmdDto dto) {
        //todo 오류 검사 추가하기 ex) name length, NULL 여부 등
        User newEmployer = new User(dto.getName(), dto.getAddress());
        User saveEmployer = userRepository.save(newEmployer);
        return saveEmployer.getId();
    }

    @Override
    public UserCmdDto update(UUID employerId, UserCmdDto dto) {
        //todo 오류 검사 추가하기
        User findUser = userRepository.findById(employerId).orElseThrow();
        findUser.update(dto.getName(), dto.getAddress());
        return entityToDto(findUser);
    }

    @Override
    public void delete(UUID userId) {
        //todo 오류 검사 추가하기
        User findUser = userRepository.findById(userId).orElseThrow();
        userRepository.delete(findUser);
    }

    UserCmdDto entityToDto(User user) {
        return new UserCmdDto(user.getName(), user.getAddress());
    }
}
