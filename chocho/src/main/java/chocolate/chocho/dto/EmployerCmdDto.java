package chocolate.chocho.dto;

import chocolate.chocho.entity.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployerCmdDto {

    private String  name;
    private Address address;
}
