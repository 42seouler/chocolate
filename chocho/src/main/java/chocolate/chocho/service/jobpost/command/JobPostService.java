package chocolate.chocho.service.jobpost.command;

import chocolate.chocho.dto.JobPostCmdDto;

import java.util.UUID;

public interface JobPostService {

    Long create(UUID storeId, JobPostCmdDto Dto);


}
