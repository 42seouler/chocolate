package chocolate.chocho.service.user.query;

import chocolate.chocho.dto.UserQueryDto;
import chocolate.chocho.entity.User;
import chocolate.chocho.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    @Override
    public UserQueryDto findById(UUID employerId) {
        //todo 오류 처리 추가하기
        User employer = userRepository.findById(employerId).orElseThrow();
        return convertToDto(employer);
    }

    @Override
    public Page<UserQueryDto> findAll(int page, int size) {
        //todo 오류 처리 추가하기
        Page<User> users = userRepository.findAll(PageRequest.of(page, size));
        return users.map(this::convertToDto);
    }

    UserQueryDto convertToDto(User user) {
        return new UserQueryDto(user.getName(), user.getAddress());
    }
}