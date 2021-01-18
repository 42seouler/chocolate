package chocolate.chocho.service.jobpost.command;

import chocolate.chocho.dto.JobPostCmdDto;

import java.util.UUID;

public interface JobPostService {

    Long            create(Long storeMgmtId, JobPostCmdDto Dto);

    JobPostCmdDto   update(Long postId, Long storeMgmtId, JobPostCmdDto dto);

    void            delete(Long postId, Long storeMgmtId);
}
