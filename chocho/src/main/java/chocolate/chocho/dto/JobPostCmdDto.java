package chocolate.chocho.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class JobPostCmdDto {

    private String title;
    private String body;

    public JobPostCmdDto(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
