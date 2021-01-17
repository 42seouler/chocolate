package chocolate.chocho.dto;

import chocolate.chocho.entity.JobOpening;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class JobPostCmdDto {

    private String      title;
    private String      body;
    private JobOpening  jobOpening;

    public JobPostCmdDto(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public JobPostCmdDto(String title, String body, JobOpening jobOpening) {
        this.title = title;
        this.body = body;
        this.jobOpening = jobOpening;
    }
}
