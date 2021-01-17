package chocolate.chocho.dto;

import chocolate.chocho.entity.JobOpening;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class StoreMgmtQueryDto {

    private StoreQueryDto   storeQueryDto;
    private JobOpening      jobOpening;

    public StoreMgmtQueryDto(StoreQueryDto storeQueryDto, JobOpening jobOpening) {
        this.storeQueryDto = storeQueryDto;
        this.jobOpening = jobOpening;
    }
}
