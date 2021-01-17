package chocolate.chocho.service.user.command;

import chocolate.chocho.dto.UserCmdDto;

import java.util.UUID;

public interface UserCmdService {
    UUID create(UserCmdDto dto);

    UserCmdDto update(UUID userId, UserCmdDto dto);

    void            delete(UUID userId);
}
